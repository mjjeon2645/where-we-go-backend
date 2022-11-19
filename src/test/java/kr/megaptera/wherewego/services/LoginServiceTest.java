package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.security.crypto.argon2.*;
import org.springframework.security.crypto.password.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class LoginServiceTest {
    private LoginService loginService;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder ;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = new Argon2PasswordEncoder();

        loginService = new LoginService(userRepository, passwordEncoder);

        User user = User.fake("angel2645@naver.com");
        user.changePassword("Tester1234", passwordEncoder);

        given(userRepository.findByEmail("angel2645@naver.com"))
            .willReturn(Optional.of(user));
    }

    @Test
    void loginSuccess() {
        User found = loginService.login("angel2645@naver.com", "Tester1234");

        assertThat(found.nickName()).isEqualTo("nickname");
    }

    @Test
    void loginFailedWithIncorrectEmail() {
        assertThrows(LoginFailedException.class, () -> {
            loginService.login("angel1234@naver.com", "Tester1234");
        });
    }

    @Test
    void loginFailedWithIncorrectPassword() {
        assertThrows(LoginFailedException.class, () -> {
            loginService.login("angel2645@naver.com", "xxx");
        });
    }
}
