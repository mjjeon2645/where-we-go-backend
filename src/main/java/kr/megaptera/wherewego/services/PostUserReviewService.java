package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional
public class PostUserReviewService {
    private final UserReviewRepository userReviewRepository;
    private final UserRepository userRepository;

    public PostUserReviewService(UserReviewRepository userReviewRepository,
                                 UserRepository userRepository) {
        this.userReviewRepository = userReviewRepository;
        this.userRepository = userRepository;
    }

    public UserReview create(MyReviewDto myReviewDto, String socialLoginId) {

        User found = userRepository.findBySocialLoginId(socialLoginId)
            .orElseThrow(AuthenticationError::new);

        String nickname = found.nickname();

        UserReview userReview = new UserReview(myReviewDto.getPlaceId(),
            found.id(), myReviewDto.getRate(), nickname, myReviewDto.getBody(), myReviewDto.getDateOfVisit());

        return userReviewRepository.save(userReview);
    }
}
