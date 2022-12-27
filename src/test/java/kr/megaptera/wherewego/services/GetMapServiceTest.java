package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class GetMapServiceTest {
    private PlaceRepository placeRepository;
    private GetMapService getMapService;

    private Place place;
    private List<Place> places;

    @BeforeEach
    void setUp() {
        placeRepository = mock(PlaceRepository.class);
        getMapService = new GetMapService(placeRepository);

        place = new Place();

        places = List.of(
            place.fake1(1L, "자연"),
            place.fake2(2L, "키즈존 맛집")
        );

        given(placeRepository.findAll()).willReturn(places);

        given(placeRepository.findAllByAddressSido("경기")).willReturn(List.of(place.fake1(1L, "자연")));

        given(placeRepository.findAllByAddressSido("서울")).willReturn(List.of(place.fake2(2L, "키즈존 맛집")));

        given(placeRepository.findAllByCategory("자연"))
            .willReturn(List.of(place.fake1(1L, "자연")));

        given(placeRepository.findAllByCategory("키즈존 맛집"))
            .willReturn(List.of(place.fake2(2L, "키즈존 맛집")));

        given(placeRepository.findById(1L)).willReturn(Optional.ofNullable(place.fake1(1L, "자연")));
    }

    @Test
    void filteredPlacesAndFindAllPlaces() {
        String sido = "전체";
        String sigungu = "전체";
        String category = "전체";

        assertThat(getMapService.filteredPlaces(sido, sigungu, category))
            .isEqualTo(places);
    }

    @Test
    void filterWithAllConditionsAndFindPlaces() {
        String sido = "서울";
        String sigungu = "성동구";
        String category = "키즈존 맛집";

        assertThat(getMapService.filteredPlaces(sido, sigungu, category))
            .isEqualTo(List.of(place.fake2(2L, "키즈존 맛집")));
    }

    @Test
    void filterWithAllConditionsAndFindNoPlaces() {
        String sido = "강원";
        String sigungu = "홍천군";
        String category = "키즈존 맛집";

        assertThat(getMapService.filteredPlaces(sido, sigungu, category))
            .isEqualTo(List.of());
    }

    @Test
    void filterWithSidoAndFindPlaces() {
        String sido = "경기";
        String sigungu = "전체";
        String category = "전체";

        assertThat(getMapService.filteredPlaces(sido, sigungu, category))
            .isEqualTo(List.of(place.fake1(1L, "자연")));
    }

    @Test
    void filterWithSidoAndFindNoPlaces() {
        String sido = "제주";
        String sigungu = "전체";
        String category = "전체";

        assertThat(getMapService.filteredPlaces(sido, sigungu, category))
            .isEqualTo(List.of());
    }

    @Test
    void filterWithSidoAndSigunguConditionsAndFindPlaces() {
        String sido = "경기";
        String sigungu = "과천시";
        String category = "전체";

        assertThat(getMapService.filteredPlaces(sido, sigungu, category))
            .isEqualTo(List.of(place.fake1(1L, "자연")));
    }

    @Test
    void filterWithSidoAndSigunguConditionsAndFindNoPlaces() {
        String sido = "충남";
        String sigungu = "당진시";
        String category = "전체";

        assertThat(getMapService.filteredPlaces(sido, sigungu, category))
            .isEqualTo(List.of());
    }

    @Test
    void filterWithSidoAndCategoryConditionsAndFindPlaces() {
        String sido = "경기";
        String sigungu = "전체";
        String category = "자연";

        assertThat(getMapService.filteredPlaces(sido, sigungu, category))
            .isEqualTo(List.of(place.fake1(1L, "자연")));
    }

    @Test
    void filterWithSidoAndCategoryConditionsAndFindNoPlaces() {
        String sido = "경기";
        String sigungu = "전체";
        String category = "유적지";

        assertThat(getMapService.filteredPlaces(sido, sigungu, category))
            .isEqualTo(List.of());
    }

    @Test
    void filterWithOnlyCategoryConditionsAndFindPlaces() {
        String sido = "전체";
        String sigungu = "전체";
        String category = "키즈존 맛집";

        assertThat(getMapService.filteredPlaces(sido, sigungu, category))
            .isEqualTo(List.of(place.fake2(2L, "키즈존 맛집")));
    }

    @Test
    void selectedPlace() {
        Long placeId = 1L;

        Place place = new Place();

        assertThat(getMapService.selectedPlace(placeId)).isEqualTo(place.fake1(1L, "자연"));
    }
}
