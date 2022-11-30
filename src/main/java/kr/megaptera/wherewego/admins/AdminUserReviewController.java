package kr.megaptera.wherewego.admins;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("admin-user-reviews")
public class AdminUserReviewController {
    private final GetUserReviewService getUserReviewService;

    public AdminUserReviewController(GetUserReviewService getUserReviewService) {
        this.getUserReviewService = getUserReviewService;
    }

    @GetMapping
    public UserReviewsDto allUserReviews() {
        List<UserReviewDto> userReviews = getUserReviewService.allReviews();
        return new UserReviewsDto(userReviews);
    }
}
