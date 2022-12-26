package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.errorDtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.services.*;
import kr.megaptera.wherewego.utils.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("blog-reviews")
public class BlogReviewController {
    private final NaverBlogUtil naverBlogUtil;
    private final GetBlogReviewService getBlogReviewService;

    public BlogReviewController(NaverBlogUtil naverBlogUtil,
                                GetBlogReviewService getBlogReviewService) {
        this.naverBlogUtil = naverBlogUtil;
        this.getBlogReviewService = getBlogReviewService;
    }

    @GetMapping("{placeId}")
    public List<BlogReviewDto> blogReviewsOfThePlace(
        @PathVariable() Long placeId
    ) {
        String keyword = getBlogReviewService.keyword(placeId);

        List<Map<String, String>> naverBlogs = naverBlogUtil.search(keyword);

        return getBlogReviewService.blogReviews(naverBlogs, placeId);
    }

    @ExceptionHandler(PlaceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto placeNotFoundError() {
        return new PlaceNotFoundErrorDto();
    }
}
