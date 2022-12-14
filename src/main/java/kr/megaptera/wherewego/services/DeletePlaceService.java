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
public class DeletePlaceService {
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final UserReviewRepository userReviewRepository;
    private final AdminRepository adminRepository;
    private final AdminLogRepository adminLogRepository;
    private final PasswordEncoder passwordEncoder;

    public DeletePlaceService(UserRepository userRepository,
                              PlaceRepository placeRepository,
                              UserReviewRepository userReviewRepository,
                              AdminRepository adminRepository,
                              AdminLogRepository adminLogRepository,
                              PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
        this.userReviewRepository = userReviewRepository;
        this.adminRepository = adminRepository;
        this.adminLogRepository = adminLogRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AdminLog delete(Long id, String socialLoginId, DeletePlaceRequestDto deletePlaceRequestDto) {
        String reason = deletePlaceRequestDto.getReason();
        String adminPassword = deletePlaceRequestDto.getPassword();

        Admin foundAdmin = adminRepository.findBySocialLoginId(socialLoginId)
            .orElseThrow(AuthenticationError::new);

        boolean isCorrectAdmin = foundAdmin.authenticate(adminPassword, passwordEncoder);

        if (!isCorrectAdmin) {
            throw new AdminPasswordError();
        }

        if (reason == null || reason.trim().equals("")) {
            throw new EmptyReasonException();
        }

        // 유저의 북마크의 장소 id가 해당 아이디면 지워줘야 함
        List<User> users = userRepository.findAll();

        for (User user : users) {
            int size = user.bookmarks().size();

            if (size == 0) {
                continue;
            }

            for (int j = size - 1; j >= 0; j -= 1) {
                Bookmark found = user.bookmarks().get(j);

                if (Objects.equals(found.getPlaceId(), id)) {
                    user.bookmarks().remove(found);
                }
            }
        }

        // 유저 리뷰의 placeId가 해당 아이디면 지워줘야 함
        userReviewRepository.deleteAllByPlaceId(id);

        placeRepository.deleteById(id);

        AdminLog createdAdminLog = new AdminLog(foundAdmin.id(),
            Event.deletePlace(), reason);

        adminLogRepository.save(createdAdminLog);

        return createdAdminLog;
    }
}
