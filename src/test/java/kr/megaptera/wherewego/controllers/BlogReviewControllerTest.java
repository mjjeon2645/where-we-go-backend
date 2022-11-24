package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BlogReviewController.class)
class BlogReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetBlogReviewService getBlogReviewService;

    @BeforeEach
    void setUp() {
        given(getBlogReviewService.blogReviews(5L)).willReturn(List.of(
            new BlogReview(5L, 5L, "아기랑 놀러다녀왔어요~", "tester", "2022.10.23.", "imageUrl", "url"),
            new BlogReview(6L, 5L, "아기랑 놀러다녀왔어요~22", "tester", "2022.10.23.", "imageUrl", "url")
        ));
    }

    @Test
    void blogReviews() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/blog-reviews/5"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"imageSource\":")
            ));
    }
}
