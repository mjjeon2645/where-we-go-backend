package kr.megaptera.wherewego.models;

import org.junit.jupiter.api.*;
import org.springframework.security.crypto.argon2.*;
import org.springframework.security.crypto.password.*;

import static org.assertj.core.api.Assertions.*;

class UserTest {
    private PasswordEncoder passwordEncoder;

   @BeforeEach
   void setUp() {
       passwordEncoder = new Argon2PasswordEncoder();
   }

    @Test
    void authenticate() {
        User user = User.fake("angel2645@naver.com");

        user.changePassword("Tester1234", passwordEncoder);

        assertThat(user.authenticate("Tester1234", passwordEncoder)).isTrue();
        assertThat(user.authenticate("XXX", passwordEncoder)).isFalse();
    }
}
