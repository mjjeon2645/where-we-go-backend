//package kr.megaptera.wherewego.admins;
//
//import kr.megaptera.wherewego.dtos.*;
//import kr.megaptera.wherewego.models.*;
//import kr.megaptera.wherewego.services.*;
//import kr.megaptera.wherewego.utils.*;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.boot.test.autoconfigure.web.servlet.*;
//import org.springframework.boot.test.mock.mockito.*;
//import org.springframework.test.web.servlet.*;
//import org.springframework.test.web.servlet.request.*;
//
//import java.util.*;
//
//import static org.mockito.BDDMockito.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(AdminPlaceController.class)
//class AdminPlaceControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private GetPlaceService getPlaceService;
//
//    @SpyBean
//    private JwtUtil jwtUtil;
//
//    @BeforeEach
//    void setUp() {
//        String socialLoginId = "socialLoginId";
//
//        given(getPlaceService.places(socialLoginId)).willReturn(List.of(
//
//        ))
////        given(getPlaceService.selectedPlace(5L, socialLoginId)).willReturn(Place.fake1(5L, "자연"));
////
////        given(getPlaceService.places(socialLoginId)).willReturn(List.of(
////            new PlaceDto(1L, "테스트장소", new PositionDto(35.0D, 128.0D), )
////        ))
//    }
//
//    @Test
//    void places() throws Exception {
//        String accessToken = jwtUtil.encode("socialLoginId");
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/admin-places")
//                .header("Authorization", "Bearer " + accessToken))
//            .andExpect(status().isOk());
//    }
//
//    @Test
//    void selectedPlace() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/admin-places/5"))
//            .andExpect(status().isOk());
//    }
//}
