package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
public class GetBlogReviewService {
    private BlogReviewRepository blogReviewRepository;

    public GetBlogReviewService(BlogReviewRepository blogReviewRepository) {
        this.blogReviewRepository = blogReviewRepository;
    }

    public List<BlogReview> blogReviews(Long placeId) {
        return blogReviewRepository.findAllByPlaceId(placeId);
    }
}
