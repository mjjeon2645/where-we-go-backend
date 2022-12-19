package kr.megaptera.wherewego.admins;

import kr.megaptera.wherewego.annotations.*;
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

@WebMvcTest(AdminSessionController.class)
@MockMvcEncoding
class AdminSessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetAdminService getAdminService;

    @MockBean
    private PostLoginService postLoginService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String socialLoginId;

    @BeforeEach
    void setUp() {
        socialLoginId = "socialLoginId";

        given(getAdminService.admin(socialLoginId)).willReturn(Admin.fake(socialLoginId));

        given(getAdminService.admin("xxx")).willThrow(AuthenticationError.class);

        given(postLoginService.adminLogin("socialLoginId", "Tester123!"))
            .willReturn(Admin.fake(socialLoginId));

        given(postLoginService.adminLogin("socialLoginId", "xxx"))
            .willThrow(AdminLoginFailedException.class);
    }

    @Test
    void adminInformation() throws Exception {
        String accessToken = jwtUtil.encode(socialLoginId);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-session")
            .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("전민지")
            ));

        verify(getAdminService).admin(socialLoginId);
    }

    @Test
    void adminInformationWithWrongSocialLoginId() throws Exception {
        String accessToken = jwtUtil.encode("xxx");

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-session")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("접근 권한이 없습니다")
            ));
    }

    @Test
    void login() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin-session")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"socialLoginId\":\"socialLoginId\", \"password\":\"Tester123!\"}"))
            .andExpect(status().isCreated())
            .andExpect(content().string(
                containsString("1234")
            ));

        verify(postLoginService).adminLogin(socialLoginId, "Tester123!");
    }

    @Test
    void loginFailed() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin-session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"socialLoginId\":\"socialLoginId\", \"password\":\"xxx\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("입력하신 정보가 정확하지 않습니다. 아이디와 비밀번호를 다시 확인해주세요.")
            ));
    }
}
