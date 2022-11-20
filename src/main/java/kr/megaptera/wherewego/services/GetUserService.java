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
public class GetUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public GetUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(String email, String password) {
        // 1. email 찾고
        User foundUser = userRepository.findByEmail(email)
            .orElseThrow(() -> new LoginFailedException());

        // 2. 패스워드 비교하고
        if (!foundUser.authenticate(password, passwordEncoder)) {
            throw new LoginFailedException();
        }

        return foundUser;
    }

    public LoginResultDto socialLogin(SocialLoginProcessResultDto dto) {
        String accessToken = dto.getAccessToken();
        String refreshToken = dto.getRefreshToken();
        String nickname = dto.getNickname();
        String email = dto.getEmail();
        String socialLoginId = dto.getSocialLoginId();
        String auth = dto.getAuth();

        User foundUser = userRepository.findBySocialLoginId(socialLoginId);
        return null;
    }
}
