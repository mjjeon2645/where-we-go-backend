package kr.megaptera.wherewego.repositories;

import kr.megaptera.wherewego.models.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
    List<UserReview> findAllByPlaceIdOrderByCreatedAtDesc(Long placeId);

    List<UserReview> findAllByPlaceId(Long placeId);

    List<UserReview> findAllByUserId(Long userId);

    void deleteAllByUserId(Long userId);

    void deleteAllByPlaceId(Long placeId);
}
