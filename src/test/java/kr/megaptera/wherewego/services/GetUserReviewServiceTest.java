package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import java.time.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class GetUserReviewServiceTest {
    private GetUserReviewService getUserReviewService;
    private UserReviewRepository userReviewRepository;

    @BeforeEach
    void setUp() {
        userReviewRepository = mock(UserReviewRepository.class);

        getUserReviewService = new GetUserReviewService(userReviewRepository);

        given(userReviewRepository.findAllByPlaceIdOrderByCreatedAtDesc(4L))
            .willReturn(List.of(
                new UserReview(1L, 4L, 0L, 4L, "재밌게 다녀왔어요!", "2022-07-09",
                    LocalDateTime.of(2022, 10, 23, 10, 43, 0, 0), false ),
                new UserReview(2L, 4L, 1L, 5L, "즐거웠어요!", "2022-10-11",
                    LocalDateTime.of(2022, 10, 15, 10, 43, 0, 0), false )
            ));

        given(userReviewRepository.findAllByPlaceIdOrderByCreatedAtDesc(1L))
            .willReturn(List.of(
                new UserReview(3L, 1L, 2L, 5L, "즐거웠어요!", "2022-10-11",
                    LocalDateTime.of(2022, 10, 15, 10, 43, 0, 0), false )
            ));

        given(userReviewRepository.findAllByPlaceIdOrderByCreatedAtDesc(3L))
            .willReturn(List.of(
                new UserReview(4L, 3L, 3L, 4L, "즐거웠어요!", "2022-10-11",
                    LocalDateTime.of(2022, 10, 23, 10, 43, 0, 0), false ),
                new UserReview(5L, 3L, 4L, 5L, "즐거웠어요!", "2022-10-11",
                    LocalDateTime.of(2022, 10, 15, 10, 43, 0, 0), false ),
                new UserReview(6L, 3L, 5L, 5L, "즐거웠어요!", "2022-10-11",
                    LocalDateTime.of(2022, 10, 05, 10, 43, 0, 0), false )
            ));

        given(userReviewRepository.save(new UserReview(1L, 1L, 5L, "좋았어요!", "2022-10-23")))
            .willReturn(new UserReview(1L, 1L, 1L, 5L, "좋았어요!", "2022-10-23",
                any(), false));
    }

    @Test
    void userReviews() {
        assertThat(getUserReviewService.userReviews(4L).size()).isEqualTo(2);
        assertThat(getUserReviewService.userReviews(4L).get(0).body())
            .isEqualTo("재밌게 다녀왔어요!");
    }

    @Test
    void averageRate() {
        assertThat(getUserReviewService.averageRate(4L)).isEqualTo("4.5");
        assertThat(getUserReviewService.averageRate(1L)).isEqualTo("5");
        assertThat(getUserReviewService.averageRate(3L)).isEqualTo("4.67");
    }

    @Test
    void create() {
        MyReviewDto myReviewDto = new MyReviewDto(1L, "2022-10-23", 5L, "좋았어요!");
        UserReview createdUserReview = getUserReviewService.create(myReviewDto);

        verify(userReviewRepository).save(any());
    }
}
