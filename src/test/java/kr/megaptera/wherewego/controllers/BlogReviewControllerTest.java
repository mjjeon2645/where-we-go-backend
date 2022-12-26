package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.services.*;
import kr.megaptera.wherewego.utils.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import java.util.*;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BlogReviewController.class)
class BlogReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetBlogReviewService getBlogReviewService;

    @MockBean
    private NaverBlogUtil naverBlogUtil;

    private List<Map<String, String>> naverBlogs;

    @BeforeEach
    void setUp() {
        String keyword = "롯데월드 아기랑";

        naverBlogs = new ArrayList<>();

        Map<String, String> map = new HashMap<>();
        map.put("title", "롯데월드 아기랑 재미있어요!");
        map.put("blogger", "봄이엄마");
        map.put("postDate", "2022-08-14");
        map.put("blogLink", "blog url link");

        naverBlogs.add(map);

        given(getBlogReviewService.keyword(5L)).willReturn(keyword);

        given(naverBlogUtil.search(keyword)).willReturn(naverBlogs);

        given(getBlogReviewService.blogReviews(naverBlogs, 5L))
            .willReturn(new ArrayList<>());
    }

    @Test
    void blogReviews() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/blog-reviews/5"))
            .andExpect(status().isOk());

        verify(getBlogReviewService).blogReviews(naverBlogs, 5L);
    }
}
