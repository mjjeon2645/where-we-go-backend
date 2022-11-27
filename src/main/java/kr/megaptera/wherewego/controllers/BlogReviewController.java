package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import kr.megaptera.wherewego.utils.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping("blog-reviews")
public class BlogReviewController {
    private NaverBlogUtil naverBlogUtil;
    private final GetBlogReviewService getBlogReviewService;

    public BlogReviewController(NaverBlogUtil naverBlogUtil, 
                                GetBlogReviewService getBlogReviewService) {
        this.naverBlogUtil = naverBlogUtil;
        this.getBlogReviewService = getBlogReviewService;
    }

    @GetMapping("{placeId}")
    public BlogReviewsDto blogReviewsOfThePlace(
        @PathVariable() Long placeId
    ) {
        String response = naverBlogUtil.search("과천 서울랜드 아기랑");
        List<BlogReviewDto> blogReviews = getBlogReviewService.blogReviews(placeId)
            .stream().map(BlogReview::toDto).collect(Collectors.toList());
        return new BlogReviewsDto(blogReviews);
    }
}
