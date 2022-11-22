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
        String originalSocialLoginId = "R9QMnn20w5Z4woJff9AJdPIa3X4ydGoBAlpjBpu_j0E";

        String token = jwtUtil.encode(originalSocialLoginId);

        String socialLoginId = jwtUtil.decode(token);

        assertThat(socialLoginId).isEqualTo(originalSocialLoginId);
    }
}
