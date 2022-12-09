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
    private final PlaceRepository placeRepository;

    public GetUserReviewService(UserReviewRepository userReviewRepository,
                                UserRepository userRepository,
                                PlaceRepository placeRepository) {
        this.userReviewRepository = userReviewRepository;
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
    }

    public List<UserReviewDto> allReviews() {
        List<UserReview> foundUserReviews = userReviewRepository.findAll();

        List<UserReviewDto> createdUserReviewsDto = new ArrayList<>();

        for (UserReview picked : foundUserReviews) {
            Place found = placeRepository.findById(picked.placeId())
                .orElseThrow(PlaceNotFoundException::new);

            UserReviewDto created = new UserReviewDto(picked.id(), picked.placeId(),
                picked.userId(), picked.rate(), picked.nickname(), picked.body(),
                picked.dateOfVisit(), picked.createdAt(), picked.isDeleted(), found.name());

            createdUserReviewsDto.add(created);
        }
        return createdUserReviewsDto;
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
            return averageToString.substring(0, 3);
        }

        if (averageToString.substring(3).equals("0")) {
            return averageToString.substring(0, 3);
        }

        return averageToString;
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

    public UserReviewDto selectedReview(Long id) {
        UserReview found = userReviewRepository.findById(id)
            .orElseThrow(UserReviewNotFoundException::new);

        Place foundPlace = placeRepository.findById(found.placeId()).orElseThrow(PlaceNotFoundException::new);

        String placeName = foundPlace.name();

        return found.toDtoWithPlaceName(placeName);
    }

    public List<UserReviewDto> findAllReviewsByUserId(Long userId) {
        return userReviewRepository.findAllByUserId(userId).stream().map(
            userReview -> userReview.toDtoWithPlaceName(
                placeRepository.findById(userReview.placeId()).orElseThrow(PlaceNotFoundException::new).name()
            )
        ).collect(Collectors.toList());
    }
}
