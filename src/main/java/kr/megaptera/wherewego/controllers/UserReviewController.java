package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping("user-reviews")
public class UserReviewController {
    private final GetUserReviewService getUserReviewService;

    public UserReviewController(GetUserReviewService getUserReviewService) {
        this.getUserReviewService = getUserReviewService;
    }

    @GetMapping("{placeId}")
    public UserReviewsDto userReviews(
        @PathVariable Long placeId
    ) {
        List<UserReviewDto> userReviews = getUserReviewService.userReviews(placeId)
            .stream().map(UserReview::toDto).collect(Collectors.toList());

        String averageRate = getUserReviewService.averageRate(placeId);

        return new UserReviewsDto(averageRate, userReviews);
    }

    @PostMapping("{placeId}")
    public UserReviewDto myReview(
        @RequestBody MyReviewDto myReviewDto
    ) {
        //TODO. user 로그인 정보 가져와서 활용해야 함

        UserReview userReview = getUserReviewService.create(myReviewDto);

        return userReview.toDto();
    }
}
