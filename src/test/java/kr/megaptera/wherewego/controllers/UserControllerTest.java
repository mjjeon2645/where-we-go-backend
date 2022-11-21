package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetUserService getUserService;

    @BeforeEach
    void setUp() {
        given(getUserService.information(1L))
            .willReturn(User.fake("angel2645@naver.com"));
    }

    @Test
    void userInformation() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"email\":\"angel2645@naver.com\"")
            ));
    }
}
