package kr.megaptera.wherewego.dtos;

import java.util.*;

public class UserReviewsDto {
    private String averageRate;

    private List<UserReviewDto> userReviews;

    public UserReviewsDto() {
    }

    public UserReviewsDto(String averageRate, List<UserReviewDto> userReviews) {
        this.averageRate = averageRate;
        this.userReviews = userReviews;
    }

    public String getAverageRate() {
        return averageRate;
    }

    public List<UserReviewDto> getUserReviews() {
        return userReviews;
    }
}
