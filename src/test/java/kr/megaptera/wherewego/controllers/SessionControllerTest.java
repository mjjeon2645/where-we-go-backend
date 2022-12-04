package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import kr.megaptera.wherewego.utils.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.context.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrialSessionController.class)
@ActiveProfiles("test")
class SessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostLoginService postLoginService;

    @SpyBean
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        given(postLoginService.login("angel2645@naver.com", "Tester1234"))
            .willReturn(User.fake("angel2645@naver.com"));

        given(postLoginService.login("angel2645@naver.com", "xxx"))
            .willThrow(new LoginFailedException());
    }

    @Test
    void login() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"angel2645@naver.com\", \"password\":\"Tester1234\"}"))
            .andExpect(status().isCreated());
    }

    @Test
    void loginFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"angel2645@naver.com\", \"password\":\"xxx\"}"))
            .andExpect(status().isBadRequest());
    }
}
