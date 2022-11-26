package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import kr.megaptera.wherewego.utils.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class UpdateUserServiceTest {
    private UpdateUserService updateUserService;
    private UserRepository userRepository;
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        jwtUtil = new JwtUtil("SECRET");
        updateUserService = new UpdateUserService(userRepository, jwtUtil);

        User user = new User(1L, "encodedPassword", "email", "또또누나", "id",
            "kakao", "unregistered", List.of());

        User user2 = new User(2L, "encodedPassword", "email", "오예오예", "id2",
            "kakao", "unregistered", List.of());

        given(userRepository.findBySocialLoginId("id")).willReturn(Optional.of(user));
        given(userRepository.findBySocialLoginId("id2")).willReturn(Optional.of(user2));
        given(userRepository.findByNickname("또또누나")).willReturn(user);
        given(userRepository.findByNickname("오예오예")).willReturn(user2);
    }

    @Test
    void updateWithNewUser() {
        assertThat(updateUserService.update("id", new SetNicknameDto("민지룽룽")).getNickname())
            .isEqualTo("민지룽룽");
    }

    @Test
    void updateWithUnchangedNickname() {
        assertThrows(UnchangedNicknameException.class, () -> {
            updateUserService.update("id", new SetNicknameDto("또또누나"));
        });
    }

    @Test
    void updateWithDuplicatedNickname() {
        assertThrows(NicknameDuplicatedException.class, () -> {
            updateUserService.update("id2", new SetNicknameDto("또또누나"));
        });
    }
}
