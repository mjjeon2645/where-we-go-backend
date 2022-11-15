package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    }

    @Test
    void topThreePlaces() {

        assertThat(getTopThreePlacesService.topThreePlaces().size()).isEqualTo(3);
    }
}
