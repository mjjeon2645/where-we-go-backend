package kr.megaptera.wherewego.admins;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("admin-places")
public class PlaceController {
    private final GetPlaceService getPlaceService;

    public PlaceController(GetPlaceService getPlaceService) {
        this.getPlaceService = getPlaceService;
    }

    @GetMapping
    public PlacesDto places() {
        List<PlaceDto> places = getPlaceService.places()
            .stream().map(Place::toPlaceDto)
            .toList();

        return new PlacesDto(places);
    }
}
