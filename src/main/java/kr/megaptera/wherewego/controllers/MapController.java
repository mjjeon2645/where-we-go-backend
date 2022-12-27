package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.errorDtos.*;
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
    private final GetMapService getMapService;

    public MapController(GetMapService getMapService) {
        this.getMapService = getMapService;
    }

    @GetMapping
    public PlacesDto filteredPlaces(
        @RequestParam(required = false, defaultValue = "전체") String sido,
        @RequestParam(required = false, defaultValue = "전체") String sigungu,
        @RequestParam(required = false, defaultValue = "전체") String category
    ) {
        List<PlaceDto> places = getMapService.filteredPlaces(sido, sigungu, category)
            .stream().map(Place::toPlaceDto).collect(Collectors.toList());

        return new PlacesDto(places);
    }

    @ExceptionHandler(RegionNotSelected.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto regionNotSelected() {
        return new RegionNotSelectedErrorDto();
    }

    @ExceptionHandler(CategoryNotSelected.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto categoryNotSelected() {
        return new CategoryNotSelectedErrorDto();
    }

    @ExceptionHandler(FilteredResultsNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto filteredResultsNotFound() {
        return new FilteredResultsNotFoundErrorDto();
    }
}
