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

class GetUserServiceTest {
    private GetUserService getUserService;
    private UserRepository userRepository;
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        jwtUtil = new JwtUtil("SECRET");
        getUserService = new GetUserService(userRepository, jwtUtil);

        User user = new User(1L, "encodedPassword", "email", "또또누나", "id",
            "kakao", "unregistered");

        User user2 = new User(2L, "encodedPassword", "email", "오예오예", "id",
            "kakao", "unregistered");

        given(userRepository.findById(1L)).willReturn(Optional.of(user));
        given(userRepository.findById(2L)).willReturn(Optional.of(user2));
        given(userRepository.findByNickname("또또누나")).willReturn(user);
        given(userRepository.findByNickname("오예오예")).willReturn(user2);
    }

    @Test
    void informationWithExistUser() {
        assertThat(getUserService.information(1L).nickname()).isEqualTo("또또누나");
        assertThat(getUserService.information(1L).state()).isEqualTo("unregistered");
    }

    @Test
    void informationWithNotExistUser() {
       assertThrows(UserNotFoundException.class, () -> {
           getUserService.information(3L);
       });
    }

    @Test
    void updateWithNewUser() {
        assertThat(getUserService.update(1L, new SetNicknameDto("민지룽룽")).getNickname())
            .isEqualTo("민지룽룽");
    }

    @Test
    void updateWithUnchangedNickname() {
        assertThrows(UnchangedNicknameException.class, () -> {
            getUserService.update(1L, new SetNicknameDto("또또누나"));
        });
    }

    @Test
    void updateWithDuplicatedNickname() {
        assertThrows(NicknameDuplicatedException.class, () -> {
            getUserService.update(2L, new SetNicknameDto("또또누나"));
        });
    }
}
