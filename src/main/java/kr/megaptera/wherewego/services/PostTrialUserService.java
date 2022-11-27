package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
public class PostTrialUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public PostTrialUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(TrialLoginRequestDto trialLoginRequestDto) {
        String socialLoginId = trialLoginRequestDto.getTrialId();
        String password = trialLoginRequestDto.getPassword();
        List<Bookmark> bookmarks = new ArrayList<>();

        System.out.println(socialLoginId);
        System.out.println("************");

        User found = userRepository.findBySocialLoginId(socialLoginId).orElse(null);

        System.out.println(found.toString());
        System.out.println("************");

        if (found != null) {
            return found;
        }

        User user = new User(socialLoginId, "tester@tester.com", "테스터", password,
            "admin", User.REGISTERED, bookmarks);

        user.changePassword(password, passwordEncoder);

        userRepository.save(user);

        return user;

    }
}
