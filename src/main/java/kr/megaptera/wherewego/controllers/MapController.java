package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping("map")
public class MapController {
  private final MapService mapService;

  public MapController(MapService mapService) {
    this.mapService = mapService;
  }

  @GetMapping
  public PlacesDto filteredPlaces(
      @RequestParam(required = false, defaultValue = "전체") String sido,
      @RequestParam(required = false, defaultValue = "전체") String sigungu,
      @RequestParam(required = false, defaultValue = "전체") String category
  ) {
    List<PlaceDto> places = mapService.filteredPlaces(sido, sigungu, category)
        .stream().map(Place::toPlaceDto).collect(Collectors.toList());

    for (PlaceDto place : places) {
      System.out.println(place);
    }

    return new PlacesDto(places);
  }

  @ExceptionHandler(RegionFilterNotSelected.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String regionFilterNotSelected() {
    return "가고싶은 지역을 선택해주세요!";
  }

  @ExceptionHandler(CategoryFilterNotSelected.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String categoryFilterNotSelected() {
    return "가고싶은 장소의 유형을 선택해주세요!";
  }

  @ExceptionHandler(FilteredResultsNotFound.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String filteredResultsNotFound() {
    return "검색 결과가 없습니다(알 수 없는 에러)";
  }
}
