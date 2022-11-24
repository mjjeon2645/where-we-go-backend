package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class GetTopThreePlacesServiceTest {
    private GetTopThreePlacesService getTopThreePlacesService;
    private PlaceRepository placeRepository;
    private UserReviewRepository userReviewRepository;

    @BeforeEach
    void setUp() {
        placeRepository = mock(PlaceRepository.class);
        userReviewRepository = mock(UserReviewRepository.class);

        getTopThreePlacesService = new GetTopThreePlacesService(
            placeRepository, userReviewRepository);

        given(placeRepository.findAll()).willReturn(List.of(
            Place.fake1(1L, "자연"),
            Place.fake1(2L, "키즈존 맛집"),
            Place.fake1(3L, "유적지"),
            Place.fake1(4L, "자연")
        ));

        given(placeRepository.getReferenceById(1L)).willReturn(Place.fake1(1L, "자연"));
        given(placeRepository.getReferenceById(2L)).willReturn(Place.fake1(2L, "키즈존 맛집"));
        given(placeRepository.getReferenceById(3L)).willReturn(Place.fake1(3L, "유적지"));
        given(placeRepository.getReferenceById(4L)).willReturn(Place.fake1(4L, "자연"));

        given(userReviewRepository.findAll()).willReturn(List.of(
            UserReview.fakeForTopThree(1L, 5L),
            UserReview.fakeForTopThree(2L, 4L),
            UserReview.fakeForTopThree(2L, 4L),
            UserReview.fakeForTopThree(3L, 3L),
            UserReview.fakeForTopThree(3L, 3L),
            UserReview.fakeForTopThree(4L, 1L),
            UserReview.fakeForTopThree(4L, 1L)
        ));
    }

    @Test
    void topThreePlaces() {
        assertThat(getTopThreePlacesService.topThreePlaces().size()).isEqualTo(3);
        assertThat(getTopThreePlacesService.topThreePlaces().get(0).getPlaceId()).isEqualTo(1L);
    }
}
