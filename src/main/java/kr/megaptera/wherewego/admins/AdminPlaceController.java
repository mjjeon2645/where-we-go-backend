package kr.megaptera.wherewego.admins;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.errorDtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("admin-places")
public class AdminPlaceController {
    private final GetPlaceService getPlaceService;
    private final PostPlaceService postPlaceService;
    private final DeletePlaceService deletePlaceService;

    public AdminPlaceController(GetPlaceService getPlaceService,
                                PostPlaceService postPlaceService,
                                DeletePlaceService deletePlaceService) {
        this.getPlaceService = getPlaceService;
        this.postPlaceService = postPlaceService;
        this.deletePlaceService = deletePlaceService;
    }

    @GetMapping
    public PlacesDto places(
        @RequestAttribute String socialLoginId
    ) {
        List<PlaceDto> places = getPlaceService.places(socialLoginId)
            .stream().map(Place::toPlaceDto)
            .toList();

        return new PlacesDto(places);
    }

    @GetMapping("{id}")
    public PlaceDto selectedPlace(
        @PathVariable Long id,
        @RequestAttribute String socialLoginId
    ) {
        return getPlaceService.selectedPlace(id, socialLoginId).toPlaceDto();
    }

    @PostMapping("new")
    @ResponseStatus(HttpStatus.CREATED)
    public PlaceDto addPlace(
        @RequestBody PlaceRequestDto placeRequestDto,
        @RequestAttribute String socialLoginId
    ) {
        return postPlaceService.create(placeRequestDto, socialLoginId).toPlaceDto();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlace(
        @PathVariable Long id
    ) {
        deletePlaceService.delete(id);
    }

    @ExceptionHandler(AddressMissingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto addressMissingError() {
        return new AddressMissingErrorDto();
    }

    @ExceptionHandler(CategoryMissingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto categoryMissingError() {
        return new CategoryMissingErrorDto();
    }

    @ExceptionHandler(AuthenticationError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto authenticationError() {
        return new AuthenticationErrorDto();
    }
}
