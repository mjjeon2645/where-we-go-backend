package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import kr.megaptera.wherewego.utils.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
public class PostLoginService {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public PostLoginService(UserRepository userRepository, AdminRepository adminRepository,
                            PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResultDto socialLogin(SocialLoginProcessResultDto dto) {
        String nickname = dto.getNickname();
        String email = dto.getEmail();
        String socialLoginId = dto.getSocialLoginId();
        String authBy = dto.getAuthBy();

        User foundUser = userRepository.findBySocialLoginId(socialLoginId).orElse(null);

        // 1. 신규 유저일 떄
        if (foundUser == null) {
            // memo. 소셜 로그인의 경우 비밀번호 입력을 받지 않으므로 socialLoginId를 비밀번호로 취급하고자 함
            String passwordForSocialLogin = socialLoginId;
            List<Bookmark> bookmarks = new ArrayList<>();

            User user = new User(passwordForSocialLogin, email, "",
                socialLoginId, authBy, "unregistered", bookmarks);
            user.changePassword(passwordForSocialLogin, passwordEncoder);

            userRepository.save(user);

            // memo. 소셜 로그인은 해당 유저의 소셜로그인 아이디로 액세스 토큰 생성
            String accessToken = jwtUtil.encode(socialLoginId);

            return new LoginResultDto(user.id(), accessToken, nickname, user.state());
        }

        // 2. 기존 유저일 때
        // memo. 소셜 로그인은 해당 유저의 소셜로그인 아이디로 액세스 토큰 생성
        String accessToken = jwtUtil.encode(socialLoginId);

        return new LoginResultDto(foundUser.id(), accessToken, foundUser.nickname(),
            foundUser.state());
    }

    public Admin adminLogin(String socialLoginId, String password) {
        // 1. 기존 어드민 사용자 여부 확인
        Admin found = adminRepository.findBySocialLoginId(socialLoginId)
            .orElseThrow(AdminLoginFailedException::new);

        // 2. 패스워드 일치 여부 확인
        if(!found.authenticate(password, passwordEncoder)) {
            throw new AdminLoginFailedException();
        }

        return found;
    }

    public User login(String identifier, String password) {
        // 1. 기존 유저 여부 확인
        User foundUser = userRepository.findBySocialLoginId(identifier)
            .orElseThrow(LoginFailedException::new);

        // 2. 패스워드 비교하고
        if (!foundUser.authenticate(password, passwordEncoder)) {
            throw new LoginFailedException();
        }

        return foundUser;
    }
}
