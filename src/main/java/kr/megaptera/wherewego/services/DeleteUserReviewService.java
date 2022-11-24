package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional
public class DeleteUserReviewService {
    private final UserReviewRepository userReviewRepository;

    public DeleteUserReviewService(UserReviewRepository userReviewRepository) {
        this.userReviewRepository = userReviewRepository;
    }

    public void delete(Long reviewId) {
        userReviewRepository.deleteById(reviewId);
    }
}
