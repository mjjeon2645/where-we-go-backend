package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("top-three-places")
public class TopThreePlacesController {
    private final GetTopThreePlacesService getTopThreePlacesService;

    public TopThreePlacesController(GetTopThreePlacesService getTopThreePlacesService) {
        this.getTopThreePlacesService = getTopThreePlacesService;
    }

    @GetMapping
    public TopThreePlacesDto topThreePlaces() {
        List<TopThreePlaceDto> topThreePlaces = getTopThreePlacesService.topThreePlaces();
        return new TopThreePlacesDto(topThreePlaces);
    }
}
