package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional
public class AdminSignUpService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminSignUpService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Admin signUp(AdminRequestDto adminRequestDto) {
        String name = adminRequestDto.getName();
        String socialLoginId = adminRequestDto.getSocialLoginId();
        Long employeeIdentificationNumber = adminRequestDto.getEmployeeIdentificationNumber();
        String password = adminRequestDto.getPassword();
        String profileImage = adminRequestDto.getProfileImage();

        // 1. 기존에 가입한 이력이 있는 회원인지 확인
        Admin admin = adminRepository
            .findByEmployeeIdentificationNumber(employeeIdentificationNumber)
            .orElse(null);

        if (admin != null) {
            throw new ExistAdminMemberException();
        }

        // 2. 중복되는 아이디인지 확인
        Admin adminFoundBySocialLoginId = adminRepository.findBySocialLoginId(socialLoginId)
            .orElse(null);

        if (adminFoundBySocialLoginId != null) {
            throw new DuplicateIdentifierException();
        }

        // 3. 문제 없는 경우 회원가입 진행
        Admin createdAdmin =
            new Admin(null, socialLoginId, password, name,
                employeeIdentificationNumber, profileImage, null);

        createdAdmin.changePassword(password, passwordEncoder);

        adminRepository.save(createdAdmin);

        return createdAdmin;
    }
}
