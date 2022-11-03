package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("map")
public class MapController {
  private MapService mapService;

  public MapController(MapService mapService) {
    this.mapService = mapService;
  }

//  @GetMapping
//  public PlacesDto places() {
//    List<PlaceDto> places = mapService.places()
//        .stream().map(Place::toPlaceDto)
//        .toList();
//
//    return new PlacesDto(places);
//  }

  @GetMapping
  public PlacesDto filteredPlaces(
      @RequestParam(required = false, defaultValue = "전체") String sido,
      @RequestParam(required = false, defaultValue = "전체") String sigungu,
      @RequestParam(required = false, defaultValue = "전체") String category
  ) {
    List<PlaceDto> places = mapService.filteredPlaces(sido, sigungu, category)
        .stream().map(Place::toPlaceDto)
        .toList();

    return new PlacesDto(places);
  }

  @ExceptionHandler(FilteredResultsNotFound.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String filteredResultsNotFound() {
    return "검색 결과가 없습니다";
  }
}
