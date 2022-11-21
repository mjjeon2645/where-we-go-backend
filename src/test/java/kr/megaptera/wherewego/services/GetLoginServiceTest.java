package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import kr.megaptera.wherewego.utils.*;
import org.junit.jupiter.api.*;
import org.springframework.security.crypto.argon2.*;
import org.springframework.security.crypto.password.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetLoginServiceTest {
    private GetLoginService getloginService;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder ;
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = new Argon2PasswordEncoder();
        jwtUtil = new JwtUtil("SECRET");

        getloginService = new GetLoginService(userRepository, passwordEncoder, jwtUtil);

        User user = User.fake("angel2645@naver.com");
        user.changePassword("Tester1234", passwordEncoder);

        given(userRepository.findByEmail("angel2645@naver.com"))
            .willReturn(Optional.of(user));
    }

    @Test
    void loginSuccess() {
        User found = getloginService.login("angel2645@naver.com", "Tester1234");

        assertThat(found.nickname()).isEqualTo("nickname");
    }

    @Test
    void loginFailedWithIncorrectEmail() {
        assertThrows(LoginFailedException.class, () -> {
            getloginService.login("angel1234@naver.com", "Tester1234");
        });
    }

    @Test
    void loginFailedWithIncorrectPassword() {
        assertThrows(LoginFailedException.class, () -> {
            getloginService.login("angel2645@naver.com", "xxx");
        });
    }
}
