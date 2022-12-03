package kr.megaptera.wherewego.dtos;

import java.util.*;

public class UserWithBookmarkedPlacesDto {
    private UserDto user;

    private List<BookmarkedPlaceDto> bookmarkedPlaces;

    private List<ChildDto> children;

    public UserWithBookmarkedPlacesDto(UserDto user,
                                       List<BookmarkedPlaceDto> bookmarkedPlaces,
                                       List<ChildDto> children) {
        this.user = user;
        this.bookmarkedPlaces = bookmarkedPlaces;
        this.children = children;
    }

    public UserDto getUserDto() {
        return user;
    }

    public List<BookmarkedPlaceDto> getBookmarkedPlaces() {
        return bookmarkedPlaces;
    }

    public List<ChildDto> getChildren() {
        return children;
    }
}
