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

  public List<Place> filteredPlaces(String sido, String sigungu, String category) {
    List<Place> result = new ArrayList<>(placeRepository.findAll());

    // 0-1. 지역 항목이 제대로 선택되지 않은 경우
    if (sido.equals("선택") || sido.equals("") ||
        sigungu.equals("선택") || sigungu.equals("")) {
      throw new RegionFilterNotSelected();
    }

    // 0-2. 카테고리 항목이 제대로 선택되지 않은 경우
    if (category.equals("선택") || category.equals("")) {
      throw new CategoryFilterNotSelected();
    }

      // 1. 모든 옵션을 전체로 선택했을 경우
      if (sido.equals("전체") && sigungu.equals("전체") && category.equals("전체")) {
        return result;
      }

    // 2. 모든 옵션을 다 선택했을 경우
    if (!sido.equals("전체") && !sigungu.equals("전체") && !category.equals("전체")) {
      return result.stream()
          .filter(value -> value.sido().equals(sido)
              && value.sigungu().equals(sigungu)
              && value.category().equals(category))
          .collect(Collectors.toList());
    }

    // 3. 시도만 선택(시도 선택, 시군구는 "전체", 카테고리도 전체)
    if (!sido.equals("전체") && sigungu.equals("전체") && category.equals("전체")) {
      return new ArrayList<>(placeRepository.findAllBySido(sido));
    }

    // 4. 시도 선택, 시군구도 선택, 카테고리는 전체
    if (!sido.equals("전체") && !sigungu.equals("전체") && category.equals("전체")) {
      return result.stream()
          .filter(value -> value.sido().equals(sido)
              && value.sigungu().equals(sigungu))
          .collect(Collectors.toList());
    }

//     5. 시도 선택, 시군구는 전체, 카테고리는 선택
    if (!sido.equals("전체") && sigungu.equals("전체") && !category.equals("전체")) {
      return result.stream()
          .filter(value -> value.sido().equals(sido)
              && value.category().equals(category))
          .collect(Collectors.toList());
    }

    // 6. 시도 전체, 시군구 전체, 카테고리는 선택
    if(sido.equals("전체") && sigungu.equals("전체") && !category.equals("전체")) {
        return result.stream()
            .filter(value -> value.category().equals(category))
            .collect(Collectors.toList());
    }
    return result;
  }
}
