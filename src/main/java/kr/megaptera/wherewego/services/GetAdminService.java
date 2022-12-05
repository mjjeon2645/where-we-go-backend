package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional
public class GetAdminService {
    private final AdminRepository adminRepository;

    public GetAdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin admin(String socialLoginId) {

        return adminRepository.findBySocialLoginId(socialLoginId)
            .orElseThrow(AuthenticationError::new);
    }
}
