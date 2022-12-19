package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
public class GetUserService {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    public GetUserService(UserRepository userRepository,
                          AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    public List<User> users(String socialLoginId) {
        Admin found = adminRepository.findBySocialLoginId(socialLoginId)
            .orElseThrow(AuthenticationError::new);

        return userRepository.findAll();
    }

    public User user(Long id, String socialLoginId) {
        adminRepository.findBySocialLoginId(socialLoginId)
            .orElseThrow(AuthenticationError::new);

        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User information(String socialLoinId) {
        return userRepository.findBySocialLoginId(socialLoinId)
            .orElseThrow(UserNotFoundException::new);
    }
}
