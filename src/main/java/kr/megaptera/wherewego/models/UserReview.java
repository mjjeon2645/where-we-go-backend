package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.*;

@Entity
public class UserReview {

    @Id
    @GeneratedValue
    private Long id;

    private Long placeId;

    private Long userId;

    private Long rate;

    private String nickname;

    private String body;

    private String dateOfVisit;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private Boolean isDeleted;


    public static UserReview fakeForTopThree(Long placeId, Long rate) {
        return new UserReview(1L, placeId, 1L, rate, "또또누나", "안녕", "",
            LocalDateTime.of(2022, 10, 4, 10, 43, 0, 0), false);
    }

    public UserReview() {
    }

    public UserReview(Long placeId, Long userId, Long rate, String nickname, String body, String dateOfVisit) {
        this.placeId = placeId;
        this.userId = userId;
        this.rate = rate;
        this.nickname = nickname;
        this.body = body;
        this.dateOfVisit = dateOfVisit;
        this.isDeleted = false;
    }

    public UserReview(Long id, Long placeId, Long userId, Long rate, String nickname, String body,
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

    public Long id() {
        return id;
    }

    public Long placeId() {
        return placeId;
    }

    public Long userId() {
        return userId;
    }

    public Long rate() {
        return rate;
    }

    public String nickname() {
        return nickname;
    }

    public String body() {
        return body;
    }

    public String dateOfVisit() {
        return dateOfVisit;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public Boolean isDeleted() {
        return isDeleted;
    }

    public UserReviewDto toDto() {
        return new UserReviewDto(id, placeId, userId, rate, nickname, body, dateOfVisit,
            createdAt, isDeleted);
    }

    @Override
    public String toString() {
        return "id: " + id + " placeId: " + placeId + " userId: " + userId + " rate: " + rate +
            " nickname: " + nickname + " body: " + body + " dateOfVisit: " +
            dateOfVisit + " createdAt: " + createdAt +
            " isDeleted: " + isDeleted;
    }
}
