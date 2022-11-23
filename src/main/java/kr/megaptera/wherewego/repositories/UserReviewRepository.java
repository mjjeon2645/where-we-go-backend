package kr.megaptera.wherewego.repositories;

import kr.megaptera.wherewego.models.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
    List<UserReview> findAllByPlaceIdOrderByCreatedAtDesc(Long placeId);

    List<UserReview> findAllByPlaceId(Long placeId);

    Optional<UserReview> findByUserId(Long userId);
}
