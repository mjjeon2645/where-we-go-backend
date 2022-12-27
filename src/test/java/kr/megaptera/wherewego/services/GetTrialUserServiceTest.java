package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.security.crypto.argon2.*;
import org.springframework.security.crypto.password.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetTrialUserServiceTest {
    private GetTrialUserService getTrialUserService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = new Argon2PasswordEncoder();

        getTrialUserService =
            new GetTrialUserService(userRepository, passwordEncoder);
    }

    @Test
    void trialUser() {
        User createdUser = getTrialUserService.trialUser();

        assertThat(createdUser.authBy()).isEqualTo("admin");
    }
}
