package kr.megaptera.wherewego.controllers;

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
            .willReturn(User.fake("angel2645@naver.com"));
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

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nickname\":\"ttotto\"}"))
            .andExpect(status().isCreated());
    }
}
