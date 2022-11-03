package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class MapServiceTest {
  private PlaceRepository placeRepository;
  private MapService mapService;
  private BusinessHours businessHours1;
  private ImageSource imageSource1;

  @BeforeEach
  void setUp() {
    placeRepository = mock(PlaceRepository.class);
    mapService = new MapService(placeRepository);

    Address address1 = new Address("경기도 과천시 광명로 181", "경기도", "과천시", 1L);
    Address address2 = new Address("경기도 가평군 설악면 미사리 320-1", "경기도", "가평군", 2L);
    Address address3 = new Address("충청남도 아산시 용화동 483-6", "충청도", "아산시", 3L);

    businessHours1 = new BusinessHours(
        "월요일: 10:01~18:00",
        "화요일: 10:02~18:00",
        "수요일: 10:03~18:00",
        "목요일: 10:04~18:00",
        "금요일: 10:05~18:00",
        "토요일: 10:06~18:00",
        "일요일: 10:07~18:00",
        1L
    );

    imageSource1 = new ImageSource(
        "https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png",
        "https://user-images.githubusercontent.com/104840243/198858244-29a83802-3ebe-42c8-894a-151c0962b8da.png",
        "https://user-images.githubusercontent.com/104840243/198858249-0e5eb65b-1a68-4549-bace-b906aa550413.png",
        1L
    );

    List<Place> places = List.of(
        new Place(1L, "과천 서울랜드", 37.434156D, 127.020126D, address1, "자연", businessHours1, imageSource1),
        new Place(2L, "설악", 37.434156D, 127.020126D, address2, "키즈존 맛집", businessHours1, imageSource1),
        new Place(3L, "좋은곳", 37.434156D, 127.020126D, address3, "유적지", businessHours1, imageSource1)
    );

    given(placeRepository.findAll()).willReturn(places);
    given(placeRepository.findAllByCategory("키즈존 맛집"))
        .willReturn(
            List.of(new Place(2L, "설악", 37.434156D, 127.020126D, address2,
                "키즈존 맛집", businessHours1, imageSource1)));
  }

  @Test
  void places() {
    assertThat(mapService.places().size()).isEqualTo(1);
    assertThat(mapService.places().get(0).category()).isEqualTo("자연");
  }

  @Test
  void filteredPlacesWithResults() {
    String sido = "경기도";
    String sigungu = "가평군";
    String category = "키즈존 맛집";

    Address address2 = new Address("경기도 가평군 설악면 미사리 320-1", "경기도", "가평군", 2L);
    assertThat(mapService.filteredPlaces(sido, sigungu, category))
        .isEqualTo(List.of(new Place(2L, "설악", 37.434156D, 127.020126D, address2,
        "키즈존 맛집", businessHours1, imageSource1)));
  }

  @Test
  void filteredPlacesWithoutResults() {
    String sido = "강원도";
    String sigungu = "가평군";
    String category = "키즈존 맛집";

    assertThrows(FilteredResultsNotFound.class, () -> {
      mapService.filteredPlaces(sido, sigungu, category);
    });
  }
}
