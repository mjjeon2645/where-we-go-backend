package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
public class DeleteUserService {
    private final UserRepository userRepository;
    private final ChildRepository childRepository;
    private final UserReviewRepository userReviewRepository;
    private final AdminRepository adminRepository;
    private final AdminLogRepository adminLogRepository;
    private final PasswordEncoder passwordEncoder;

    public DeleteUserService(UserRepository userRepository,
                             ChildRepository childRepository,
                             UserReviewRepository userReviewRepository,
                             AdminRepository adminRepository,
                             AdminLogRepository adminLogRepository,
                             PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.childRepository = childRepository;
        this.userReviewRepository = userReviewRepository;
        this.adminRepository = adminRepository;
        this.adminLogRepository = adminLogRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void deleteTrialUser(String socialLoginId) {
        User foundToDelete = userRepository.findBySocialLoginId(socialLoginId)
            .orElseThrow(UserNotFoundException::new);

        deleteProcess(foundToDelete);
    }

    public AdminLog deleteUser(Long id, String socialLoginId, DeleteUserRequestDto deleteUserRequestDto) {
        String password = deleteUserRequestDto.getPassword();
        String reason = deleteUserRequestDto.getReason();

        Admin foundAdmin = adminRepository.findBySocialLoginId(socialLoginId)
            .orElseThrow(AuthenticationError::new);

        boolean isCorrectAdmin = foundAdmin.authenticate(password, passwordEncoder);

        if (!isCorrectAdmin) {
            throw new AdminPasswordError();
        }

        if (reason == null || reason.trim().equals("")) {
            throw new EmptyReasonException();
        }

        User foundToDelete = userRepository.findById(id)
            .orElseThrow(UserNotFoundException::new);

        deleteProcess(foundToDelete);

        AdminLog createdAdminLog = new AdminLog(foundAdmin.id(),
            Event.deleteUser(), reason);

        adminLogRepository.save(createdAdminLog);

        return createdAdminLog;
    }

    public void deleteProcess(User foundToDelete) {
        List<Bookmark> bookmarks = foundToDelete.bookmarks();

        if (bookmarks != null && bookmarks.size() > 0) {
            bookmarks.subList(0, bookmarks.size()).clear();
        }

        Long userId = foundToDelete.id();

        childRepository.deleteAllByUserId(userId);

        userReviewRepository.deleteAllByUserId(userId);

        userRepository.deleteById(userId);
    }
}
