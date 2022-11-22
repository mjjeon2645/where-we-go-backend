package kr.megaptera.wherewego.dtos;

import java.util.*;

public class BookmarkedPlaceDtos {
    private List<BookmarkedPlaceDto> bookmarkedPlaces;

    public BookmarkedPlaceDtos() {
    }

    public BookmarkedPlaceDtos(List<BookmarkedPlaceDto> bookmarkedPlaces) {
        this.bookmarkedPlaces = bookmarkedPlaces;
    }

    public List<BookmarkedPlaceDto> getBookmarkedPlaces() {
        return bookmarkedPlaces;
    }
}
