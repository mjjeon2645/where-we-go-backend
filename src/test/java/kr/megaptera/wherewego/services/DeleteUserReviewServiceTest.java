package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.security.crypto.argon2.*;
import org.springframework.security.crypto.password.*;

import java.time.*;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class DeleteUserReviewServiceTest {
    private DeleteUserReviewService deleteUserReviewService;
    private UserReviewRepository userReviewRepository;
    private AdminRepository adminRepository;
    private AdminLogRepository adminLogRepository;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userReviewRepository = mock(UserReviewRepository.class);
        adminRepository = mock(AdminRepository.class);
        adminLogRepository = mock(AdminLogRepository.class);
        passwordEncoder = new Argon2PasswordEncoder();

        deleteUserReviewService = new DeleteUserReviewService(userReviewRepository,
            adminRepository, adminLogRepository, passwordEncoder);

        Admin foundAdmin = new Admin(5L, "adminSocialLoginId", passwordEncoder.encode("password"),
            "전민지", 1234L, "profileImage", LocalDateTime.of(2022, 10, 10, 10, 43, 0, 0));

        given(adminRepository.findBySocialLoginId("adminSocialLoginId"))
            .willReturn(Optional.of(foundAdmin));

        given(adminRepository.findBySocialLoginId("xxx"))
            .willThrow(AuthenticationError.class);
    }

    @Test
    void delete() {
        Long reviewId = 2L;

        deleteUserReviewService.delete(reviewId);

        verify(userReviewRepository).deleteById(2L);
    }

    @Test
    void deleteAndCreateAdminLog() {
        DeleteReviewRequestDto deleteReviewRequestDto =
            new DeleteReviewRequestDto("비속어 포함", "password");

        AdminLog createdAdminLog =
            deleteUserReviewService.delete(2L, "adminSocialLoginId", deleteReviewRequestDto);

        assertThat(createdAdminLog.reason()).isEqualTo("비속어 포함");
        assertThat(createdAdminLog.event().title()).isEqualTo("deleteUserReview");

        verify(adminRepository).findBySocialLoginId("adminSocialLoginId");
    }
}
