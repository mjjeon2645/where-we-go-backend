package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.annotations.*;
import kr.megaptera.wherewego.dtos.*;
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

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserReviewController.class)
@MockMvcEncoding
class UserReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetUserReviewService getUserReviewService;

    @MockBean
    private PostUserReviewService postUserReviewService;

    @MockBean
    private DeleteUserReviewService deleteUserReviewService;

    @SpyBean
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        Long placeId = 4L;

        given(getUserReviewService.userReviews(placeId)).willReturn(
            List.of(new UserReview(1L, placeId, 10L, 3L, "또또누나", "정말정말 좋아요!", "2022-01-05",
                LocalDateTime.of(2022, 10, 8, 10, 43, 0, 0), false),
            new UserReview(2L, placeId, 11L, 5L, "민지룽룽", "정말정말 좋아요~~!", "2022-03-08",
                LocalDateTime.of(2022, 10, 10, 10, 43, 0, 0), false)
        ));

        given(getUserReviewService.averageRate(placeId)).willReturn("4.0");

        given(getUserReviewService.findUserReview(placeId, "socialLoginId"))
            .willReturn(new UserReviewDto(1L, placeId, 10L, 3L, "또또누나", "정말정말 좋아요!",
                    "2021-01-05", LocalDateTime.of(2022, 10, 8, 10, 43, 0, 0), false));

        given(postUserReviewService.create(any(), eq("socialLoginId")))
            .willReturn(new UserReview(10L, 5L, 3L, 5L, "민지룽룽", "멋진곳!", "2022-01-05",
                LocalDateTime.of(2022, 10, 10, 10, 43, 0, 0), false));
    }

    @Test
    void userReviews() throws Exception {
        String accessToken = jwtUtil.encode("socialLoginId");

        mockMvc.perform(MockMvcRequestBuilders.get("/user-reviews/4")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("정말정말 좋아요!")
            ));

        verify(getUserReviewService).userReviews(4L);
        verify(getUserReviewService).averageRate(4L);
        verify(getUserReviewService).findUserReview(4L, "socialLoginId");
    }

    @Test
    void myReview() throws Exception {
        String accessToken = jwtUtil.encode("socialLoginId");

        mockMvc.perform(MockMvcRequestBuilders.post("/user-reviews/5")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"placeId\":4, \"dateOfVisit\":\"2022-01-05\", \"rate\":5, " +
                    "\"body\":\"멋진곳!\"}"))
            .andExpect(status().isCreated())
            .andExpect(content().string(
                containsString("멋진곳!")
            ));
    }

    @Test
    void deleteMyReview() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user-reviews/8"))
            .andExpect(status().isNoContent());
    }
}
