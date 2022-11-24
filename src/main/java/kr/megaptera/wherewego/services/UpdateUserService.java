package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import kr.megaptera.wherewego.utils.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional
public class UpdateUserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UpdateUserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public CreatedUserDto update(Long userId, SetNicknameDto setNicknameDto) {
        String nickname = setNicknameDto.getNickname();

        User userFoundByNickname = userRepository.findByNickname(nickname);
        User found = userRepository.findById(userId)
            .orElseThrow(UserNotFoundException::new);

        // 1. 자신의 닉네임일 경우
        if (found.nickname().equals(nickname)) {
            throw new UnchangedNicknameException();
        }

        // 2. 다른 사람이 사용중일 경우
        if (userFoundByNickname != null) {
            throw new NicknameDuplicatedException();
        }

        // 3. 신규/기존 유저에 따른 별도 처리
        if (found.state().equals(User.REGISTERED)) {
            found.changeNickname(nickname);
        }

        if (found.state().equals(User.UNREGISTERED)) {
            found.register(nickname);
        }

        // 4. access token 발급 및 프로세스 종료
        String accessToken = jwtUtil.encode(found.socialLoginId());

        return new CreatedUserDto(
            found.id(), accessToken, found.nickname(), found.state()
        );
    }
}
