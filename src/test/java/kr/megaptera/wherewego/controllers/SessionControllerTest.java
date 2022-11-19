package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.context.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SessionController.class)
@ActiveProfiles("test")
class SessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetLoginService getLoginService;

    @BeforeEach
    void setUp() {
        given(getLoginService.login("angel2645@naver.com", "Tester1234"))
            .willReturn(new User(1L, "angel2645@naver.com", "Tester1234", "nickName"));

        given(getLoginService.login("angel2645@naver.com", "xxx"))
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
