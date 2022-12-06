package kr.megaptera.wherewego.dtos;

import java.time.*;
import java.util.*;

public class UserDto {
    private Long id;

    private String email;

    private String nickname;

    private String socialLoginId;

    private String authBy;

    private String state;

    private List<BookmarkedPlaceDto> bookmarkedPlaces;

    private LocalDateTime createdAt;

    public UserDto() {
    }

    public UserDto(Long id, String email, String nickname, String socialLoginId,
                   String authBy, String state, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.socialLoginId = socialLoginId;
        this.authBy = authBy;
        this.state = state;
        this.createdAt = createdAt;
    }

    public UserDto(Long id, String email, String nickname, String socialLoginId,
                   String authBy, String state, List<BookmarkedPlaceDto> bookmarkedPlaces, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.socialLoginId = socialLoginId;
        this.authBy = authBy;
        this.state = state;
        this.bookmarkedPlaces = bookmarkedPlaces;
        this.createdAt = createdAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
