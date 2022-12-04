package kr.megaptera.wherewego.repositories;

import kr.megaptera.wherewego.models.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findBySocialLoginId(String socialLoginId);

    Optional<Admin> findByEmployeeIdentificationNumber(Long employeeIdentificationNumber);
}
