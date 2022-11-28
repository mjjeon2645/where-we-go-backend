package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
public class GetTrialUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public GetTrialUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    public User create(TrialLoginRequestDto trialLoginRequestDto) {
//        String socialLoginId = trialLoginRequestDto.getTrialId();
//        String password = trialLoginRequestDto.getPassword();
//        List<Bookmark> bookmarks = new ArrayList<>();
//
//        User found = userRepository.findBySocialLoginId(socialLoginId).orElse(null);
//
//        if (found != null) {
//            return found;
//        }
//
//        User trialUser = new User(socialLoginId, "tester@tester.com", "테스터", password,
//            "admin", User.REGISTERED, bookmarks);
//
//        trialUser.changePassword(password, passwordEncoder);
//
//        userRepository.save(trialUser);
//
//        return trialUser;
//
//    }

    public User trialUser() {
        String socialLoginId = "trialUserId-" + UUID.randomUUID();

        String trialUserNickname = "테스터" + socialLoginId.split("-")[2];

        String password = "trialUserPassword";

        List<Bookmark> bookmarks = new ArrayList<>();

        User trialUser = new User(password, "tester@tester.com", trialUserNickname, socialLoginId,
            "admin", User.REGISTERED, bookmarks);

        trialUser.changePassword(password, passwordEncoder);

        userRepository.save(trialUser);

        return trialUser;
    }
}
