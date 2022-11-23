package kr.megaptera.wherewego.repositories;

import kr.megaptera.wherewego.models.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface ChildRepository extends JpaRepository<Child, Long> {
    List<Child> findAllByUserId(Long userId);
}
