package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
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
    private final UserRepository userRepository;

    public GetUserReviewService(UserReviewRepository userReviewRepository,
                                UserRepository userRepository) {
        this.userReviewRepository = userReviewRepository;
        this.userRepository = userRepository;
    }

    public List<UserReview> userReviews(Long placeId) {
        // TODO. 추후 글 삭제기능 도입시 isDeleted 값이 반영되어야 하므로 미리 filtering 로직 구현해둠
        return userReviewRepository.findAllByPlaceIdOrderByCreatedAtDesc(placeId)
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

    public UserReview create(MyReviewDto myReviewDto, String socialLoginId) {

        User found = userRepository.findBySocialLoginId(socialLoginId)
            .orElseThrow(AuthenticationError::new);

        String nickname = found.nickname();

        UserReview userReview = new UserReview(myReviewDto.getPlaceId(),
            found.id(), myReviewDto.getRate(), nickname, myReviewDto.getBody(), myReviewDto.getDateOfVisit());

        return userReviewRepository.save(userReview);
    }

    public UserReviewDto findUserReview(Long placeId, String socialLoginId) {
        List<UserReview> foundReviews = userReviewRepository.findAllByPlaceId(placeId).stream().toList();

        User found = userRepository.findBySocialLoginId(socialLoginId)
            .orElseThrow(AuthenticationError::new);

        UserReview userReview = foundReviews.stream()
            .filter(item -> item.userId().equals(found.id()))
            .findFirst()
            .orElse(null);

        if (userReview == null) {
            return null;
        }

        return userReview.toDto();
    }

    public void delete(Long reviewId) {
        userReviewRepository.deleteById(reviewId);
    }
}
