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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeletePlaceServiceTest {
    private UserRepository userRepository;
    private PlaceRepository placeRepository;
    private UserReviewRepository userReviewRepository;
    private AdminRepository adminRepository;
    private AdminLogRepository adminLogRepository;
    private PasswordEncoder passwordEncoder;
    private DeletePlaceService deletePlaceService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        placeRepository = mock(PlaceRepository.class);
        userReviewRepository = mock(UserReviewRepository.class);
        adminRepository = mock(AdminRepository.class);
        adminLogRepository = mock(AdminLogRepository.class);

        passwordEncoder = new Argon2PasswordEncoder();

        deletePlaceService = new DeletePlaceService(userRepository, placeRepository,
            userReviewRepository, adminRepository, adminLogRepository, passwordEncoder);

        Admin foundAdmin = new Admin(20L, "socialLoginId1",
            "encodedPassword", "전민지", 1234L, "profileImage",
            LocalDateTime.of(2022, 10, 10, 10, 43, 0, 0));

        foundAdmin.changePassword(foundAdmin.encodedPassword(), passwordEncoder);

        given(adminRepository.findBySocialLoginId("socialLoginId1"))
            .willReturn(Optional.of(foundAdmin));

        given(adminRepository.findBySocialLoginId("xxx"))
            .willThrow(AuthenticationError.class);

        given(userRepository.findAll()).willReturn(List.of(
            User.fake1("mjjeon2645@gmail.com"),
            User.fake2("angel2645@naver.com")
        ));
    }

    @Test
    void delete() {
        DeletePlaceRequestDto deletePlaceRequestDto =
            new DeletePlaceRequestDto("장소가 사라짐", "encodedPassword");

        AdminLog createdAdminLog = deletePlaceService.delete(3L, "socialLoginId1", deletePlaceRequestDto);

        assertThat(createdAdminLog.reason()).isEqualTo("장소가 사라짐");
        assertThat(createdAdminLog.event().title()).isEqualTo("deletePlace");

        verify(userReviewRepository).deleteAllByPlaceId(3L);
        verify(placeRepository).deleteById(3L);
        verify(adminLogRepository).save(createdAdminLog);
    }

    @Test
    void deleteWithWrongAdminId() {
        DeletePlaceRequestDto deletePlaceRequestDto =
            new DeletePlaceRequestDto("장소가 사라짐", "encodedPassword");

        assertThrows(AuthenticationError.class, () -> {
            deletePlaceService.delete(3L, "xxx", deletePlaceRequestDto);
        });
    }

    @Test
    void deleteWithNoReason() {
        DeletePlaceRequestDto deletePlaceRequestDto =
            new DeletePlaceRequestDto("", "encodedPassword");

        assertThrows(EmptyReasonException.class, () -> {
            deletePlaceService.delete(3L, "socialLoginId1", deletePlaceRequestDto);
        });
    }
}
