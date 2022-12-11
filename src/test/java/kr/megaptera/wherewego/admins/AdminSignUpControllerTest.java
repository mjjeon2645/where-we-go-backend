//package kr.megaptera.wherewego.admins;
//
//import kr.megaptera.wherewego.services.*;
//import kr.megaptera.wherewego.utils.*;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.boot.test.autoconfigure.web.servlet.*;
//import org.springframework.boot.test.mock.mockito.*;
//import org.springframework.test.web.servlet.*;
//import org.springframework.test.web.servlet.request.*;
//import org.springframework.web.bind.annotation.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(AdminSignUpController.class)
//class AdminSignUpControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private GetUserService getUserService;
//
//    @MockBean
//    private GetBookmarkService getBookmarkService;
//
//    @MockBean
//    private GetChildService getChildService;
//
//    @MockBean
//    private DeleteUserService deleteUserService;
//
//    @SpyBean
//    private JwtUtil jwtUtil;
//
//    @BeforeEach
//    void setUp() {
//        //
//    }
//
//    @Test
//    void users() throws Exception {
//        String accessToken = jwtUtil.encode(socialLoginId);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/")
//                .header("Authorization", "Bearer " + accessToken))
//            .andExpect(status().isOk());
//    }
//
//}