package kr.megaptera.wherewego.dtos;

import java.util.*;

public class BlogReviewsDto {
    private List<BlogReviewDto> blogReviews;

    public BlogReviewsDto() {
    }

    public BlogReviewsDto(List<BlogReviewDto> blogReviews) {
        this.blogReviews = blogReviews;
    }

    public List<BlogReviewDto> getBlogReviews() {
        return blogReviews;
    }
}
