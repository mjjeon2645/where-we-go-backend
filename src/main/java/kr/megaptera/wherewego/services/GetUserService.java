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
public class GetUserService {
    private UserRepository userRepository;
    private JwtUtil jwtUtil;

    public GetUserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public User information(Long userId) {
        User found = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException());

        return found;
    }

    public CreatedUserDto create(Long userId, UserInformationDto userInformationDto) {
        User found = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException());

        found.register(userInformationDto);

        String accessToken = jwtUtil.encode(found.email());
        return new CreatedUserDto(
            found.id(), found.nickname(), accessToken, found.state()
        );
    }

    public CreatedUserDto update(Long userId, UserInformationDto userInformationDto) {
        User found = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException());

        found.changeNickname(userInformationDto);

        String accessToken = jwtUtil.encode(found.email());

        // CreatedUserDto 재활용
        return new CreatedUserDto(
            found.id(), found.nickname(), accessToken, found.state()
        );
    }
}
