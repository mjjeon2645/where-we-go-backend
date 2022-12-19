package kr.megaptera.wherewego.admins;

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

import java.time.*;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminUserReviewController.class)
@MockMvcEncoding
class AdminUserReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetUserReviewService getUserReviewService;

    @MockBean
    private DeleteUserReviewService deleteUserReviewService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String socialLoginId;

    @BeforeEach
    void setUp() {
        socialLoginId = "socialLoginId";

        given(getUserReviewService.allReviews(socialLoginId))
            .willReturn(List.of(
               new UserReviewDto(1L, 3L, 2L, 5L, "민지룽룽", "여기 정말 좋아요~", "2022-01-01",
                   LocalDateTime.of(2022, 12, 8, 10, 43, 0, 0), false, "과천 서울랜드"),
                new UserReviewDto(10L, 3L, 11L, 3L, "카카오룽룽", "괜찮네요~", "2021-01-01",
                    LocalDateTime.of(2022, 11, 8, 10, 43, 0, 0), false, "과천 서울랜드")
            ));

        given(getUserReviewService.allReviews("xxx")).willThrow(AuthenticationError.class);

        given(getUserReviewService.selectedReview(5L, socialLoginId))
            .willReturn(new UserReviewDto(1L, 3L, 2L, 5L, "민지룽룽", "여기 정말 좋아요~", "2022-01-01",
                LocalDateTime.of(2022, 12, 8, 10, 43, 0, 0), false, "과천 서울랜드"));

        given(getUserReviewService.selectedReview(10L, socialLoginId))
            .willThrow(UserReviewNotFoundException.class);

        given(getUserReviewService.findAllReviewsByUserId(5L))
            .willReturn(List.of(
                new UserReviewDto(1L, 3L, 5L, 5L, "민지룽룽", "여기 정말 좋아요~", "2022-01-01",
                    LocalDateTime.of(2022, 12, 8, 10, 43, 0, 0), false, "과천 서울랜드"),
                new UserReviewDto(10L, 4L, 5L, 3L, "민지룽룽", "괜찮네요~", "2021-01-01",
                    LocalDateTime.of(2022, 11, 8, 10, 43, 0, 0), false, "서울숲 공원")
            ));
    }

    @Test
    void allUserReviews() throws Exception {
        String accessToken = jwtUtil.encode(socialLoginId);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-user-reviews")
            .header("Authorization", "Bearer " + accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("여기 정말 좋아요~")
            ));

        verify(getUserReviewService).allReviews(socialLoginId);
    }

    @Test
    void allUserReviewsWithWrongSocialLoginId() throws Exception {
        String accessToken = jwtUtil.encode("xxx");

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-user-reviews")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("접근 권한이 없습니다")
            ));
    }

    @Test
    void selectedUserReview() throws Exception {
        String accessToken = jwtUtil.encode(socialLoginId);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-user-reviews/5")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("민지룽룽")
            ));

        verify(getUserReviewService).selectedReview(5L, socialLoginId);
    }

    @Test
    void selectedUserReviewWithWrongReviewId() throws Exception {
        String accessToken = jwtUtil.encode(socialLoginId);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-user-reviews/10")
            .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("해당 리뷰를 찾을 수 없습니다")
            ));
    }

    @Test
    void findAllReviewsByUserId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin-user-reviews/userId/5")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("서울숲 공원")
            ));

        verify(getUserReviewService).findAllReviewsByUserId(5L);
    }

    @Test
    void deleteUserReviewAndCreateLog() throws Exception {
        given(deleteUserReviewService.delete(eq(11L), eq(socialLoginId), any(DeleteReviewRequestDto.class)))
            .willReturn(new AdminLog(60L, 50L, new Event(200L, "deleteUserReview"),
                "비속어 포함", LocalDateTime.of(2022, 11, 8, 10, 43, 0, 0)));

        String accessToken = jwtUtil.encode(socialLoginId);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin-user-reviews/11")
            .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"reason\":\"비속어 포함\", \"password\":\"Tester123!\"}"))
            .andExpect(status().isCreated())
            .andExpect(content().string(
                containsString("deleteUserReview")
            ));

        verify(deleteUserReviewService).delete(eq(11L), eq(socialLoginId), any());
    }
}
