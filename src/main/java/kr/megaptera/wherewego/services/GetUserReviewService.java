package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
import java.util.stream.*;

@Service
@Transactional
public class GetUserReviewService {
    private final UserReviewRepository userReviewRepository;

    public GetUserReviewService(UserReviewRepository userReviewRepository) {
        this.userReviewRepository = userReviewRepository;
    }

    public List<UserReview> userReviews(Long placeId) {
        // TODO. 추후 글 삭제기능 도입시 isDeleted 값이 반영되어야 하므로 미리 filtering직 구현해둠
        return userReviewRepository.findAllByPlaceId(placeId)
            .stream().filter(review -> !review.isDeleted())
            .collect(Collectors.toList());
    }

    public String averageRate(Long placeId) {
        DoubleSummaryStatistics statistics =
            userReviews(placeId).stream().map(UserReview::rate).toList()
                .stream().mapToDouble(num -> num).summaryStatistics();

        double average = statistics.getAverage();

        String averageToString = String.format("%.2f", average);

        if (averageToString.substring(2).equals("00")) {
            return averageToString.substring(0, 1);
        }

        if (averageToString.substring(3).equals("0")) {
            return averageToString.substring(0, 3);
        }

        return averageToString;
    }
}
