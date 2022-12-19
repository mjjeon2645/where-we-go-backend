package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional
public class DeleteUserReviewService {
    private final UserReviewRepository userReviewRepository;
    private final AdminRepository adminRepository;
    private final AdminLogRepository adminLogRepository;
    private final PasswordEncoder passwordEncoder;

    public DeleteUserReviewService(UserReviewRepository userReviewRepository,
                                   AdminRepository adminRepository,
                                   AdminLogRepository adminLogRepository,
                                   PasswordEncoder passwordEncoder) {
        this.userReviewRepository = userReviewRepository;
        this.adminRepository = adminRepository;
        this.adminLogRepository = adminLogRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void delete(Long reviewId) {
        userReviewRepository.deleteById(reviewId);
    }

    public AdminLog delete(Long reviewId, String adminSocialLoginId,
                       DeleteReviewRequestDto deleteReviewRequestDto) {

        String password = deleteReviewRequestDto.getPassword();
        String reason = deleteReviewRequestDto.getReason();

        // 어드민의 socialLoginId, password 일치 여부를 한번 더 확인하여 삭제 진행

        Admin foundAdmin = adminRepository.findBySocialLoginId(adminSocialLoginId)
            .orElseThrow(AuthenticationError::new);

        boolean isCorrectAdmin = foundAdmin.authenticate(password, passwordEncoder);

        if (!isCorrectAdmin) {
            throw new AdminPasswordError();
        }

        if (reason == null || reason.trim().equals("")) {
            throw new EmptyReasonException();
        }

        userReviewRepository.deleteById(reviewId);

        AdminLog createdAdminLog = new AdminLog(foundAdmin.id(),
            Event.deleteUserReview(), reason);

        adminLogRepository.save(createdAdminLog);

        return createdAdminLog;
    }
}
