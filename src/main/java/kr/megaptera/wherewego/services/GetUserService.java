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
        String authBy = dto.getAuthBy();

        User foundUser = userRepository.findBySocialLoginId(socialLoginId);

        if (foundUser == null) {
            // TODO. 소셜 로그인의 경우 비밀번호 입력을 받지 않으므로 socialLoginId를 비밀번호로 취급
            String passwordForSocialLogin = socialLoginId;

            User user = new User(passwordForSocialLogin, email, nickname, socialLoginId, authBy, "unregistered");
            user.changePassword(passwordForSocialLogin, passwordEncoder);

            userRepository.save(user);

            return new LoginResultDto(user.id(), accessToken, nickname, user.state());
        }

        return new LoginResultDto(foundUser.id(), accessToken, foundUser.nickname(), foundUser.state());
    }

    public User information(Long userId) {
        return null;
    }
}
