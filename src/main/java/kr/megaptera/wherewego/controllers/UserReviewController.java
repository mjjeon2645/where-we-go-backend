package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.errorDtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping("user-reviews")
public class UserReviewController {
    private final GetUserReviewService getUserReviewService;
    private final PostUserReviewService postUserReviewService;
    private final DeleteUserReviewService deleteUserReviewService;

    public UserReviewController(GetUserReviewService getUserReviewService,
                                PostUserReviewService postUserReviewService,
                                DeleteUserReviewService deleteUserReviewService) {
        this.getUserReviewService = getUserReviewService;
        this.postUserReviewService = postUserReviewService;
        this.deleteUserReviewService = deleteUserReviewService;
    }

    @GetMapping("{placeId}")
    public UserReviewsDto userReviews(
        @PathVariable Long placeId,
        @RequestAttribute String socialLoginId
    ) {
        List<UserReviewDto> userReviews = getUserReviewService.userReviews(placeId)
            .stream().map(UserReview::toDto).collect(Collectors.toList());

        String averageRate = getUserReviewService.averageRate(placeId);

        UserReviewDto userReview = getUserReviewService.findUserReview(placeId, socialLoginId);

        return new UserReviewsDto(averageRate, userReviews, userReview);
    }

    @PostMapping("{placeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserReviewDto myReview(
        @RequestAttribute String socialLoginId,
        @RequestBody MyReviewDto myReviewDto
    ) {
        UserReview userReview = postUserReviewService.create(myReviewDto, socialLoginId);

        return userReview.toDto();
    }

    @DeleteMapping("{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMyReview(
        @PathVariable Long reviewId
    ) {
        deleteUserReviewService.delete(reviewId);
    }

    @ExceptionHandler(AuthenticationError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto authenticationError() {
        return new AuthenticationErrorDto();
    }
}
