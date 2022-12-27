package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

class GetBlogReviewServiceTest {
    private GetBlogReviewService getBlogReviewService;
    private PlaceRepository placeRepository;

    @BeforeEach
    void setUp() {
        placeRepository = mock(PlaceRepository.class);
        getBlogReviewService = new GetBlogReviewService(placeRepository);

        Place foundPlace = Place.fake1(1L, "자연");

        given(placeRepository.findById(1L)).willReturn(Optional.of(foundPlace));
    }

    @Test
    void blogReviews() {
        List<Map<String, String>> naverBlogs = new ArrayList<>();

        Map<String, String> maps = new LinkedHashMap<>();

        maps.put("title", "과천 서울랜드 아기랑 함께 간 곳");
        maps.put("blogger", "또또누나");
        maps.put("postDate", "20221020");
        maps.put("blogLink", "link");

        naverBlogs.add(maps);

        List<BlogReviewDto> blogReviews = getBlogReviewService.blogReviews(naverBlogs, 1L);

        assertThat(blogReviews.get(0).getAuthor()).isEqualTo("또또누나");
        assertThat(blogReviews.get(0).getDate()).isEqualTo("2022-10-20");
    }
}
