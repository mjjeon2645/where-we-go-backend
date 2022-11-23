package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping("blog-reviews")
public class BlogReviewController {
    private final GetBlogReviewService getBlogReviewService;

    public BlogReviewController(GetBlogReviewService getBlogReviewService) {
        this.getBlogReviewService = getBlogReviewService;
    }

    @GetMapping("{placeId}")
    public BlogReviewsDto blogReviewsOfThePlace(
        @PathVariable() Long placeId
    ) {
        List<BlogReviewDto> blogReviews = getBlogReviewService.blogReviews(placeId)
            .stream().map(BlogReview::toDto).collect(Collectors.toList());
        return new BlogReviewsDto(blogReviews);
    }
}
