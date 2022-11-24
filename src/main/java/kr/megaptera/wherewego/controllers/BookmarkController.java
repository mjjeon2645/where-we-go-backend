package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.errorDtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("bookmarks")
public class BookmarkController {
    private final GetBookmarkService getBookmarkService;
    private final PostBookmarkService postBookmarkService;

    public BookmarkController(GetBookmarkService getBookmarkService,
                              PostBookmarkService postBookmarkService) {
        this.getBookmarkService = getBookmarkService;
        this.postBookmarkService = postBookmarkService;
    }

    @GetMapping()
    public BookmarkedPlaceDtos bookmarks(
        @RequestAttribute String socialLoginId
    ) {
        List<BookmarkedPlaceDto> bookmarkedPlaces =
            getBookmarkService.bookmarks(socialLoginId);

        return new BookmarkedPlaceDtos(bookmarkedPlaces);
    }

    @PostMapping("{placeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public BookmarkedPlaceDtos toggleBookmark(
        @PathVariable Long placeId,
        @RequestAttribute String socialLoginId
    ) {
        List<BookmarkedPlaceDto> bookmarkedPlaces =
            postBookmarkService.toggle(placeId, socialLoginId);

        return new BookmarkedPlaceDtos(bookmarkedPlaces);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto userNotFound() {
        return new UserNotFoundErrorDto();
    }

    @ExceptionHandler(PlaceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto placeNotFound() {
        return new PlaceNotFoundErrorDto();
    }
}
