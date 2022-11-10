package kr.megaptera.wherewego.repositories;

import kr.megaptera.wherewego.models.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findByCategory(String category);

    List<Place> findByAddressSido(String sido);
}
