package kr.megaptera.wherewego.dtos;

import java.time.*;

public class UserReviewDto {
    private Long id;

    private Long placeId;

    private Long userId;

    private Long rate;

    private String nickname;

    private String body;

    private String dateOfVisit;

    private LocalDateTime createdAt;

    private Boolean isDeleted;

    public UserReviewDto() {
    }

    public UserReviewDto(Long id, Long placeId, Long userId, Long rate, String nickname, String body,
                         String dateOfVisit, LocalDateTime createdAt, Boolean isDeleted) {
        this.id = id;
        this.placeId = placeId;
        this.userId = userId;
        this.rate = rate;
        this.nickname = nickname;
        this.body = body;
        this.dateOfVisit = dateOfVisit;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRate() {
        return rate;
    }

    public String getNickname() {
        return nickname;
    }

    public String getBody() {
        return body;
    }

    public String getDateOfVisit() {
        return dateOfVisit;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }
}
