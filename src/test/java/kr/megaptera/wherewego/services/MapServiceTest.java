package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class MapServiceTest {
  private PlaceRepository placeRepository;
  private MapService mapService;
  private Address address1;
  private Address address2;
  private BusinessHours businessHours1;
  private ImageSource imageSource1;
  private List<Place> places;


  @BeforeEach
  void setUp() {
    placeRepository = mock(PlaceRepository.class);
    mapService = new MapService(placeRepository);

    address1 = new Address(1L, "경기도 과천시 광명로 181", "경기", "과천시");
    address2 = new Address(2L, "경기도 가평군 설악면 미사리 320-1", "경기", "가평군");

    businessHours1 = new BusinessHours(
        1L,
        "월요일: 10:01~18:00",
        "화요일: 10:02~18:00",
        "수요일: 10:03~18:00",
        "목요일: 10:04~18:00",
        "금요일: 10:05~18:00",
        "토요일: 10:06~18:00",
        "일요일: 10:07~18:00"
    );

    imageSource1 = new ImageSource(
        1L,
        "https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png",
        "https://user-images.githubusercontent.com/104840243/198858244-29a83802-3ebe-42c8-894a-151c0962b8da.png",
        "https://user-images.githubusercontent.com/104840243/198858249-0e5eb65b-1a68-4549-bace-b906aa550413.png"
    );

    places = List.of(
        new Place(1L, "과천 서울랜드", 37.434156D, 127.020126D, address1, "자연", businessHours1, imageSource1),
        new Place(2L, "설악", 37.434156D, 127.020126D, address1, "키즈존 맛집", businessHours1, imageSource1),
        new Place(3L, "좋은곳", 37.434156D, 127.020126D, address1, "유적지", businessHours1, imageSource1)
    );

    given(placeRepository.findAll()).willReturn(places);

    given(placeRepository.findByAddressSido("경기"))
        .willReturn(
            List.of(new Place(1L, "과천 서울랜드", 37.434156D, 127.020126D, address1,
                    "자연", businessHours1, imageSource1),
                new Place(2L, "설악", 37.434156D, 127.020126D, address2, "키즈존 맛집",
                    businessHours1, imageSource1)));

    given(placeRepository.findByCategory("키즈존 맛집"))
        .willReturn(
            List.of(new Place(2L, "설악", 37.434156D, 127.020126D, address2,
                "키즈존 맛집", businessHours1, imageSource1)));

    Place place = new Place();
    given(placeRepository.findById(1L)).willReturn(Optional.ofNullable(place.fake()));
  }

  @Test
  void filteredPlacesAndFindAllPlaces() {
    String sido = "전체";
    String sigungu = "전체";
    String category = "전체";

    assertThat(mapService.filteredPlaces(sido, sigungu, category))
        .isEqualTo(places);
  }

  @Test
  void filterWithAllConditionsAndFindPlaces() {
    String sido = "경기";
    String sigungu = "가평군";
    String category = "키즈존 맛집";

    assertThat(mapService.filteredPlaces(sido, sigungu, category))
        .isEqualTo(List.of(new Place(2L, "설악", 37.434156D, 127.020126D,
            address2, "키즈존 맛집", businessHours1, imageSource1)));
  }

  @Test
  void filterWithAllConditionsAndFindNoPlaces() {
    String sido = "강원";
    String sigungu = "홍천군";
    String category = "키즈존 맛집";

    assertThat(mapService.filteredPlaces(sido, sigungu, category))
        .isEqualTo(List.of());
  }

  @Test
  void filterWithSidoAndFindPlaces() {
    String sido = "경기";
    String sigungu = "전체";
    String category = "전체";

    assertThat(mapService.filteredPlaces(sido, sigungu, category))
        .isEqualTo(List.of(
            new Place(1L, "과천 서울랜드", 37.434156D, 127.020126D,
               address1, "자연", businessHours1, imageSource1),
            new Place(2L, "설악", 37.434156D, 127.020126D,
                address2, "키즈존 맛집", businessHours1, imageSource1)
        ));
  }

  @Test
  void filterWithSidoAndFindNoPlaces() {
    String sido = "제주";
    String sigungu = "전체";
    String category = "전체";

    assertThat(mapService.filteredPlaces(sido, sigungu, category))
        .isEqualTo(List.of());
  }

  @Test
  void filterWithSidoAndSigunguConditionsAndFindPlaces() {
    String sido = "경기";
    String sigungu = "가평군";
    String category = "전체";

    assertThat(mapService.filteredPlaces(sido, sigungu, category))
        .isEqualTo(List.of(new Place(2L, "설악", 37.434156D, 127.020126D,
            address2, "키즈존 맛집", businessHours1, imageSource1)));
  }

  @Test
  void filterWithSidoAndSigunguConditionsAndFindNoPlaces() {
    String sido = "충남";
    String sigungu = "당진시";
    String category = "전체";

    assertThat(mapService.filteredPlaces(sido, sigungu, category))
        .isEqualTo(List.of());
  }

  @Test
  void filterWithSidoAndCategoryConditionsAndFindPlaces() {
    String sido = "경기";
    String sigungu = "전체";
    String category = "키즈존 맛집";

    assertThat(mapService.filteredPlaces(sido, sigungu, category))
        .isEqualTo(List.of(new Place(2L, "설악", 37.434156D, 127.020126D,
            address2, "키즈존 맛집", businessHours1, imageSource1)));
  }

  @Test
  void filterWithSidoAndCategoryConditionsAndFindNoPlaces() {
    String sido = "경기";
    String sigungu = "전체";
    String category = "유적지";

    assertThat(mapService.filteredPlaces(sido, sigungu, category))
        .isEqualTo(List.of());
  }

  @ Test
  void filterWithOnlyCategoryConditionsAndFindPlaces() {
    String sido = "전체";
    String sigungu = "전체";
    String category = "키즈존 맛집";

    assertThat(mapService.filteredPlaces(sido, sigungu, category))
        .isEqualTo(List.of(new Place(2L, "설악", 37.434156D, 127.020126D,
            address2, "키즈존 맛집", businessHours1, imageSource1)));
  }

  @Test
  void selectedPlace() {
    Long placeId = 1L;

    Place place = new Place();

    assertThat(mapService.selectedPlace(placeId)).isEqualTo(place.fake());
  }
}
