package kr.megaptera.wherewego.repositories;

import kr.megaptera.wherewego.models.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface BlogReviewRepository extends JpaRepository<BlogReview, Long> {
    List<BlogReview> findAllByPlaceId(Long placeId);
}
