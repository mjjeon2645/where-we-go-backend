package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("places")
public class PlaceController {
    private MapService mapService;

    public PlaceController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("{id}")
    public PlaceDto selectedPlace(
        @PathVariable("id") Long id
    ) {
        Place selectedPlace = mapService.selectedPlace(id);

        return selectedPlace.toPlaceDto();
    }
}
