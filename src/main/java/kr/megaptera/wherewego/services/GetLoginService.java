package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import kr.megaptera.wherewego.utils.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional
public class GetLoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public GetLoginService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
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
        String accessTokenFromSocialLogin = dto.getAccessToken();
        String refreshTokenFromSocialLogin = dto.getRefreshToken();
        String nickname = dto.getNickname();
        String email = dto.getEmail();
        String socialLoginId = dto.getSocialLoginId();
        String authBy = dto.getAuthBy();

        User foundUser = userRepository.findBySocialLoginId(socialLoginId);

        // 1. 신규 유저일 떄
        if (foundUser == null) {
            // memo. 소셜 로그인의 경우 비밀번호 입력을 받지 않으므로 socialLoginId를 비밀번호로 취급하고자 함
            String passwordForSocialLogin = socialLoginId;

            User user = new User(passwordForSocialLogin, email, "",
                socialLoginId, authBy, "unregistered");
            user.changePassword(passwordForSocialLogin, passwordEncoder);

            userRepository.save(user);

            // memo. 소셜 로그인은 해당 유저의 소셜로그인 이메일 계정으로 액세스 토큰 생성
            String accessToken = jwtUtil.encode(email);

            return new LoginResultDto(user.id(), accessToken, nickname, user.state());
        }

        // 2. 기존 유저일 때
        // memo. 소셜 로그인은 해당 유저의 소셜로그인 이메일 계정으로 액세스 토큰 생성
        String accessToken = jwtUtil.encode(email);

        return new LoginResultDto(foundUser.id(), accessToken, foundUser.nickname(), foundUser.state());
    }
}
