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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteUserServiceTest {
    private UserRepository userRepository;
    private ChildRepository childRepository;
    private UserReviewRepository userReviewRepository;
    private AdminRepository adminRepository;
    private AdminLogRepository adminLogRepository;
    private PasswordEncoder passwordEncoder;
    private DeleteUserService deleteUserService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        childRepository = mock(ChildRepository.class);
        userReviewRepository = mock(UserReviewRepository.class);
        adminRepository = mock(AdminRepository.class);
        adminLogRepository = mock(AdminLogRepository.class);
        passwordEncoder = new Argon2PasswordEncoder();

        deleteUserService =
            new DeleteUserService(userRepository, childRepository, userReviewRepository,
                adminRepository, adminLogRepository, passwordEncoder);

        Admin foundAdmin = new Admin(1L, "socialLoginId", "encodedPassword", "전민지",
            1234L, "image", LocalDateTime.of(2022, 10, 10, 10, 43, 0, 0));

        foundAdmin.changePassword("encodedPassword", passwordEncoder);

        given(adminRepository.findBySocialLoginId("socialLoginId"))
            .willReturn(Optional.of(foundAdmin));

        given(adminRepository.findBySocialLoginId("xxx"))
            .willThrow(AuthenticationError.class);

        User userToDelete = User.fake2("mjjeon2645@gmail.com");

        given(userRepository.findById(2L)).willReturn(Optional.of(userToDelete));

        given(userRepository.findById(3L)).willThrow(UserNotFoundException.class);
    }

    @Test
    void deleteUser() {
        DeleteUserRequestDto deleteUserRequestDto
            = new DeleteUserRequestDto("회원 요청", "encodedPassword");

        AdminLog createdAdminLog =
            deleteUserService.deleteUser(2L, "socialLoginId", deleteUserRequestDto);

        assertThat(createdAdminLog.event().title()).isEqualTo("deleteUser");
        assertThat(createdAdminLog.reason()).isEqualTo("회원 요청");

        verify(adminRepository).findBySocialLoginId("socialLoginId");
        verify(userRepository).findById(2L);
        verify(childRepository).deleteAllByUserId(2L);
        verify(userReviewRepository).deleteAllByUserId(2L);
        verify(userRepository).deleteById(2L);
    }

    @Test
    void deleteUserWithWrongAuthentication() {
        DeleteUserRequestDto deleteUserRequestDto
            = new DeleteUserRequestDto("회원 요청", "encodedPassword");

        assertThrows(AuthenticationError.class, () -> {
            deleteUserService.deleteUser(2L, "xxx", deleteUserRequestDto);
        });
    }

    @Test
    void deleteUserWithEmptyReason() {
        DeleteUserRequestDto deleteUserRequestDto
            = new DeleteUserRequestDto("", "encodedPassword");

        assertThrows(EmptyReasonException.class, () -> {
            deleteUserService.deleteUser(2L, "socialLoginId", deleteUserRequestDto);
        });
    }

    @Test
    void deleteUserWithWrongUserId() {
        DeleteUserRequestDto deleteUserRequestDto
            = new DeleteUserRequestDto("회원 요청", "encodedPassword");

        assertThrows(UserNotFoundException.class, () -> {
            deleteUserService.deleteUser(3L, "socialLoginId", deleteUserRequestDto);
        });
    }
}
