package kr.megaptera.wherewego.models;

import org.junit.jupiter.api.*;
import org.springframework.security.crypto.argon2.*;
import org.springframework.security.crypto.password.*;

import java.util.*;

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

    @Test
    void register() {
        User user = new User(1L, "encodedPassword", "email", "또또누나", "id",
            "kakao", "unregistered", List.of());

        assertThat(user.nickname()).isEqualTo("또또누나");
        assertThat(user.state()).isEqualTo("unregistered");

        user.register("민지룽룽");

        assertThat(user.state()).isEqualTo("registered");
    }

    @Test
    void changeNickname() {
        User user = new User(1L, "encodedPassword", "email", "또또누나", "id",
            "kakao", "unregistered", List.of());

        assertThat(user.nickname()).isEqualTo("또또누나");
        assertThat(user.state()).isEqualTo("unregistered");

        user.changeNickname("민지룽룽");

        assertThat(user.state()).isEqualTo("unregistered");
    }

    @Test
    void addAndRemoveBookmark() {
        List<Bookmark> bookmarks = new ArrayList<>();

        User user = new User(1L, "PW", "email", "nick", "tester", "authBy", "registered", bookmarks);

        assertThat(user.bookmarks().size()).isEqualTo(0);

        user.addBookmark(bookmarks, 1L);
        user.addBookmark(bookmarks, 2L);

        assertThat(user.bookmarks().size()).isEqualTo(2);

        user.removeBookmark(bookmarks, 1L);

        assertThat(user.bookmarks().size()).isEqualTo(1);
    }
}
