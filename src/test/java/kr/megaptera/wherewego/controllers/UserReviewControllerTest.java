package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import java.time.*;
import java.util.*;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserReviewController.class)
class UserReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetUserReviewService getUserReviewService;

    @BeforeEach
    void setUp() {
        given(getUserReviewService.userReviews(4L)).willReturn(
            List.of(new UserReview(3L, 4L, 1L, 3L, "정말정말 좋아요!", "2022.3.5",
                    LocalDateTime.of(2022, 10, 8, 10, 43, 0, 0), false))
        );
    }

    @Test
    void userReviews() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user-reviews/4"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"dateOfVisit\":")
            ));
    }
}
