package kr.megaptera.wherewego.repositories;

import kr.megaptera.wherewego.models.*;
import org.springframework.data.jpa.repository.*;

public interface AdminLogRepository extends JpaRepository<AdminLog, Long> {
}
