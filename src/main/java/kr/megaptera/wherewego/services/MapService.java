package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
import java.util.stream.*;

@Service
@Transactional
public class MapService {
  private final PlaceRepository placeRepository;

  public MapService(PlaceRepository placeRepository) {
    this.placeRepository = placeRepository;
  }

  public List<Place> places() {
    List<Place> places = placeRepository.findAll();
    return new ArrayList<>(places);
  }

  public List<Place> filteredPlaces(String sido, String sigungu, String category) {
    List<Place> places = new ArrayList<>(placeRepository.findAll());

    // 1. 모든 옵션을 전체로 선택했을 경우
    if (sido.equals("전체") && sigungu.equals("전체") && category.equals("전체")) {
      return places;
    }

    // 2. 모든 옵션을 다 선택했을 경우
    if (!sido.equals("전체") && !sigungu.equals("전체") && !category.equals("전체")) {
      List<Place> result = places.stream()
          .filter(value -> value.address().sido().equals(sido)
              && value.address().sigungu().equals(sigungu)
              && value.category().equals(category)).toList();

      if (result.size() == 0) {
        throw new FilteredResultsNotFound();
      }

      System.out.println(result);
      return result;
    }
    return places;
  }
}
