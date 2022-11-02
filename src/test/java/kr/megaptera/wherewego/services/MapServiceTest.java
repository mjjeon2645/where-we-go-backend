package kr.megaptera.wherewego.services;

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

  @BeforeEach
  void setUp() {
    placeRepository = mock(PlaceRepository.class);
    mapService = new MapService(placeRepository);

    List<Place> places = List.of(
        new Place(1L, "과천 서울랜드", 37.434156D, 127.020126D,
            new Address(1L, "경기도 과천시 광명로 181", "경기도", "과천시", 1L), "자연",
            new BusinessHours(
                1L,
                "월요일: 10:01~18:00",
                "화요일: 10:02~18:00",
                "수요일: 10:03~18:00",
                "목요일: 10:04~18:00",
                "금요일: 10:05~18:00",
                "토요일: 10:06~18:00",
                "일요일: 10:07~18:00",
                1L),
            new ImageSource(
                1L,
                "https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png",
                "https://user-images.githubusercontent.com/104840243/198858244-29a83802-3ebe-42c8-894a-151c0962b8da.png",
                "https://user-images.githubusercontent.com/104840243/198858249-0e5eb65b-1a68-4549-bace-b906aa550413.png",
                1L)
        ));

    given(placeRepository.findAll()).willReturn(places);
  }

  @Test
  void places() {
    assertThat(mapService.places().size()).isEqualTo(1);
  }
}
