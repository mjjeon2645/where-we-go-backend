package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.annotations.*;
import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import kr.megaptera.wherewego.utils.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@MockMvcEncoding
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetUserService getUserService;

    @MockBean
    private UpdateUserService updateUserService;

    @SpyBean
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        given(getUserService.information("socialLoginId"))
            .willReturn(User.fake1("angel2645@naver.com"));

        String accessToken = jwtUtil.encode("socialLoginId");

        given(updateUserService.update("socialLoginId", "또또누나"))
            .willReturn(new CreatedUserDto(5L, accessToken, "또또누나", "registered"));

        given(updateUserService.update("socialLoginId", "민지룽룽"))
            .willThrow(UnchangedNicknameException.class);

        given(updateUserService.update("socialLoginId", "테스터"))
            .willThrow(NicknameDuplicatedException.class);
    }

    @Test
    void userInformation() throws Exception {
        String accessToken = jwtUtil.encode("socialLoginId");

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .header("Authorization", "Bearer " + accessToken))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"email\":\"angel2645@naver.com\"")
            ));
    }

    @Test
    void signUp() throws Exception {
        String accessToken = jwtUtil.encode("socialLoginId");

        mockMvc.perform(MockMvcRequestBuilders.post("/users/sign-up")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nickname\":\"또또누나\"}"))
            .andExpect(status().isCreated())
            .andExpect(content().string(
                containsString("또또누나")
            ));
    }

    @Test
    void signUpWithWrongFormatNickname() throws Exception {
        String accessToken = jwtUtil.encode("socialLoginId");

        mockMvc.perform(MockMvcRequestBuilders.post("/users/sign-up")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nickname\":\"또또누나룰루랄라야호\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("3~7자 이내의 한글,")
            ));
    }

    @Test
    void changeNickNameWithAUsingNickname() throws Exception {
        String accessToken = jwtUtil.encode("socialLoginId");

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nickname\":\"민지룽룽\"}"))
           .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("현재 사용하고 계시는 닉네임과 동일합니다")
            ));
    }

    @Test
    void changeNickNameWithContainingTester() throws Exception {
        String accessToken = jwtUtil.encode("socialLoginId");

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nickname\":\"테스터\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("이미 다른 분이 사용 중이예요")
            ));
    }
}
