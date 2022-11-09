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
@RequestMapping("places")
public class PlaceController {
    private MapService mapService;

    public PlaceController(MapService mapService) {
        this.mapService = mapService;
    }

    // API 고민중
//    @GetMapping
//    public PlacesDto filteredPlaces(
//        @RequestParam(required = false, defaultValue = "전체") String sido,
//        @RequestParam(required = false, defaultValue = "전체") String sigungu,
//        @RequestParam(required = false, defaultValue = "전체") String category
//    ) {
//        List<PlaceDto> places = mapService.filteredPlaces(sido, sigungu, category)
//            .stream().map(Place::toPlaceDto).collect(Collectors.toList());
//
//        return new PlacesDto(places);
//    }

    @GetMapping("{id}")
    public PlaceDto selectedPlace(
        @PathVariable("id") Long id
    ) {
        Place selectedPlace = mapService.selectedPlace(id);

        return selectedPlace.toPlaceDto();
    }

    @ExceptionHandler(SelectedPlaceNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String selectedPlaceNotFound() {
        return "선택한 장소의 정보를 불러오지 못했습니다.(알 수 없는 에러)";
    }
}
