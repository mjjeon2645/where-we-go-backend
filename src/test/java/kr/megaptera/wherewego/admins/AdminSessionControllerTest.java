//package kr.megaptera.wherewego.admins;
//
//import kr.megaptera.wherewego.dtos.*;
//import kr.megaptera.wherewego.exceptions.*;
//import kr.megaptera.wherewego.models.*;
//import kr.megaptera.wherewego.services.*;
//import kr.megaptera.wherewego.utils.*;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.boot.test.autoconfigure.web.servlet.*;
//import org.springframework.boot.test.mock.mockito.*;
//import org.springframework.http.*;
//import org.springframework.test.web.servlet.*;
//import org.springframework.test.web.servlet.request.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(AdminSessionController.class)
//class AdminSessionControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private PostLoginService postLoginService;
//
//    @BeforeEach
//    void setUp() {
//        given(postLoginService.adminLogin("admin123", "admin123"))
//            .willReturn(new Admin(1L, "admin123", "encodedPassword", "minji", 123L, any()));
//
//        given(postLoginService.adminLogin("admin123", "xxx"))
//            .willThrow(AdminLoginFailedException.class);
//    }
//
//    @Test
//    void login() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/admin-session")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content("{\"identifier\":\"admin123\", \"password\":\"admin123\"}"))
//            .andExpect(status().isCreated());
//    }
//
//    @Test
//    void loginFailed() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/admin-session")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"identifier\":\"admin123\", \"password\":\"xxx\"}"))
//            .andExpect(status().isBadRequest());
//    }
//}
