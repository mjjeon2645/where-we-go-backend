package kr.megaptera.wherewego.utils;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {
    static final String SECRET = "SECRET";

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil(SECRET);
    }

    @Test
    void encodeAndDecode() {
        String originalUserEmail = "angel2645@naver.com";

        String token = jwtUtil.encode(originalUserEmail);

        String userEmail = jwtUtil.decode(token);

        assertThat(userEmail).isEqualTo(originalUserEmail);
    }

    @Test
    void decodeError() {

    }
}
