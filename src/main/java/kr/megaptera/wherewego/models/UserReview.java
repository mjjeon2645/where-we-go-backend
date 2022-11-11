package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.*;

@Entity
public class UserReview {
    @Id
    @GeneratedValue
    private Long id;

    private Long placeId;

    private Long userId;

    private Long rate;

    private String body;

    private String dateOfVisit;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private Boolean isDeleted;

    public UserReview() {
    }

    public UserReview(Long id, Long placeId, Long userId, Long rate, String body,
                      String dateOfVisit, LocalDateTime createdAt, Boolean isDeleted) {
        this.id = id;
        this.placeId = placeId;
        this.userId = userId;
        this.rate = rate;
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
        return new UserReviewDto(id, placeId, userId, rate, body, dateOfVisit,
            createdAt, isDeleted);
    }
}
