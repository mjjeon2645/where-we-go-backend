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

    public GetUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User information(String socialLoinId) {
        return userRepository.findBySocialLoginId(socialLoinId)
            .orElseThrow(UserNotFoundException::new);
    }

    public List<User> users() {
        return userRepository.findAll();
    }

    public User user(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
