package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class GetUserServiceTest {
    private GetUserService getUserService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        getUserService = new GetUserService(userRepository);

        User user = new User(1L, "encodedPassword", "email", "또또누나", "id",
            "kakao", "unregistered", List.of());

        User user2 = new User(2L, "encodedPassword", "email", "오예오예", "id",
            "kakao", "unregistered", List.of());

        given(userRepository.findById(1L)).willReturn(Optional.of(user));
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
}
