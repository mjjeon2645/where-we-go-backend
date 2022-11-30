package kr.megaptera.wherewego.dtos;

import java.util.*;

public class UserReviewsDto {
    private String averageRate;

    private List<UserReviewDto> userReviews;

    private UserReviewDto userReview;

    public UserReviewsDto() {
    }

    public UserReviewsDto(List<UserReviewDto> userReviews) {
        this.userReviews = userReviews;
    }

    public UserReviewsDto(String averageRate, List<UserReviewDto> userReviews, UserReviewDto userReview) {
        this.averageRate = averageRate;
        this.userReviews = userReviews;
        this.userReview = userReview;
    }

    public String getAverageRate() {
        return averageRate;
    }

    public List<UserReviewDto> getUserReviews() {
        return userReviews;
    }

    public UserReviewDto getUserReview() {
        return userReview;
    }
}
