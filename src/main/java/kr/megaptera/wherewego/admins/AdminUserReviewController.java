package kr.megaptera.wherewego.admins;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.errorDtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("admin-user-reviews")
public class AdminUserReviewController {
    private final GetUserReviewService getUserReviewService;
    private final DeleteUserReviewService deleteUserReviewService;

    public AdminUserReviewController(GetUserReviewService getUserReviewService,
                                     DeleteUserReviewService deleteUserReviewService) {
        this.getUserReviewService = getUserReviewService;
        this.deleteUserReviewService = deleteUserReviewService;
    }

    @GetMapping
    public UserReviewsDto allUserReviews() {
        List<UserReviewDto> userReviews = getUserReviewService.allReviews();
        return new UserReviewsDto(userReviews);
    }

    @GetMapping("{id}")
    public UserReviewDto selectedUserReview(
        @PathVariable Long id
    ) {
        return getUserReviewService.selectedReview(id);
    }

    @GetMapping("userId/{id}")
    public UserReviewsDto findAllReviewsByUserId(
        @PathVariable Long id
    ) {
        List<UserReviewDto> allReviewsFindByUserId = getUserReviewService.findAllReviewsByUserId(id);

        return new UserReviewsDto(allReviewsFindByUserId);
    }

    @PostMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public String deleteUserReviewAndCreateLog(
        @PathVariable Long id,
        @RequestAttribute String socialLoginId,
        @RequestBody DeleteReviewRequestDto deleteReviewRequestDto
    ) {
        deleteUserReviewService.delete(id, socialLoginId, deleteReviewRequestDto);
        return "Success to delete the review";
    }

    @ExceptionHandler(UserReviewNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto reviewNotFoundError() {
        return new UserReviewNotFoundErrorDto();
    }

    @ExceptionHandler(AdminPasswordError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto adminPasswordError() {
        return new AdminPasswordErrorDto();
    }

    @ExceptionHandler(EmptyReasonException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto emptyReasonError() {
        return new EmptyReasonErrorDto();
    }
}
