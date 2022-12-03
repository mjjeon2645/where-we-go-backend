package kr.megaptera.wherewego.dtos;

import java.util.*;

public class UserDto {
    private Long id;

    private String email;

    private String nickname;

    private String socialLoginId;

    private String authBy;

    private String state;

    private List<BookmarkedPlaceDto> bookmarkedPlaces;

    public UserDto() {
    }

    public UserDto(Long id, String email, String nickname, String socialLoginId,
                   String authBy, String state) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.socialLoginId = socialLoginId;
        this.authBy = authBy;
        this.state = state;
    }

    public UserDto(Long id, String email, String nickname, String socialLoginId,
                   String authBy, String state, List<BookmarkedPlaceDto> bookmarkedPlaces) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.socialLoginId = socialLoginId;
        this.authBy = authBy;
        this.state = state;
        this.bookmarkedPlaces = bookmarkedPlaces;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSocialLoginId() {
        return socialLoginId;
    }

    public String getAuthBy() {
        return authBy;
    }

    public String getState() {
        return state;
    }

    public List<BookmarkedPlaceDto> getBookmarkedPlaces() {
        return bookmarkedPlaces;
    }
}
