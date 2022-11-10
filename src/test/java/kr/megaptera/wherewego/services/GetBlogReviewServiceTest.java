package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class GetBlogReviewServiceTest {
    private GetBlogReviewService getBlogReviewService;
    private BlogReviewRepository blogReviewRepository;

    @BeforeEach
    void setUp() {
        blogReviewRepository = mock(BlogReviewRepository.class);
        getBlogReviewService = new GetBlogReviewService(blogReviewRepository);

        given(blogReviewRepository.findAllByPlaceId(1L))
            .willReturn(List.of(
                new BlogReview(1L, 1L ,"12개월 아이와 다녀온 곳", "tester", "2022.10.23",
                    "imageUrl", "url")));
    }

    @Test
    void blogReviews() {
        assertThat(getBlogReviewService.blogReviews(1L).size()).isEqualTo(1);
        assertThat(getBlogReviewService.blogReviews(1L).get(0).title())
            .isEqualTo("12개월 아이와 다녀온 곳");
    }
}
