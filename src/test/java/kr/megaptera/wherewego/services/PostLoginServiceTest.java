//package kr.megaptera.wherewego.services;
//
//import kr.megaptera.wherewego.dtos.*;
//import kr.megaptera.wherewego.exceptions.*;
//import kr.megaptera.wherewego.models.*;
//import kr.megaptera.wherewego.repositories.*;
//import kr.megaptera.wherewego.utils.*;
//import org.junit.jupiter.api.*;
//import org.springframework.security.crypto.argon2.*;
//import org.springframework.security.crypto.password.*;
//
//import java.util.*;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.BDDMockito.*;
//
//class PostLoginServiceTest {
//    private PostLoginService postLoginService;
//
//    private UserRepository userRepository;
//
//    private PasswordEncoder passwordEncoder;
//    private JwtUtil jwtUtil;
//
//    @BeforeEach
//    void setUp() {
//        userRepository = mock(UserRepository.class);
//        passwordEncoder = new Argon2PasswordEncoder();
//        jwtUtil = new JwtUtil("SECRET");
//
//        postLoginService = new PostLoginService(userRepository, adminRepository, passwordEncoder, jwtUtil);
//
//        User user = User.fake("angel2645@naver.com");
//        user.changePassword("Tester1234", passwordEncoder);
//
//        given(userRepository.findByEmail("angel2645@naver.com"))
//            .willReturn(Optional.of(user));
//
//        given(userRepository.findBySocialLoginId("socialLoginId")).willReturn(null);
//
//        given(userRepository.findBySocialLoginId("socialLoginId2"))
//            .willReturn(Optional.of(new User(5L, "pw", "email", "또또누나", "socialLoginId2", "naver",
//                "registered", List.of())));
//    }
//
//    @Test
//    void loginSuccess() {
//        User found = postLoginService.login("angel2645@naver.com", "Tester1234");
//
//        assertThat(found.nickname()).isEqualTo("nickname");
//    }
//
//    @Test
//    void loginFailedWithIncorrectEmail() {
//        assertThrows(LoginFailedException.class, () -> {
//            postLoginService.login("angel1234@naver.com", "Tester1234");
//        });
//    }
//
//    @Test
//    void loginFailedWithIncorrectPassword() {
//        assertThrows(LoginFailedException.class, () -> {
//            postLoginService.login("angel2645@naver.com", "xxx");
//        });
//    }
//
//    @Test
//    void newUserWantToLoginWithSocialId() {
//        SocialLoginProcessResultDto socialLoginProcessResultDto =
//            new SocialLoginProcessResultDto(
//                "accessToken", "refreshToken", "tester", "email", "socialLoginId", "kakao");
//
//        assertNull(userRepository.findBySocialLoginId(socialLoginProcessResultDto.getSocialLoginId()));
//    }
//
//    @Test
//    void existUserWantToLoginWithSocialId() {
//        SocialLoginProcessResultDto socialLoginProcessResultDto =
//            new SocialLoginProcessResultDto(
//                "accessToken", "refreshToken", "또또누나", "email", "socialLoginId2", "naver");
//
//        assertThat(postLoginService.socialLogin(socialLoginProcessResultDto))
//            .isEqualTo(new LoginResultDto(5L, jwtUtil.encode("socialLoginId2"),
//                "또또누나", "registered"));
//    }
//}
