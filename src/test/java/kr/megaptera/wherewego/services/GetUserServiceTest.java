package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import java.time.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class GetUserServiceTest {
    private GetUserService getUserService;
    private UserRepository userRepository;
    private AdminRepository adminRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        adminRepository = mock(AdminRepository.class);

        getUserService = new GetUserService(userRepository, adminRepository);

        User user = new User(1L, "encodedPassword", "email", "또또누나", "id",
            "kakao", "unregistered", List.of(), LocalDateTime.of(2022, 10, 8, 10, 43, 0, 0));

        User user2 = new User(2L, "encodedPassword", "email", "오예오예", "id2",
            "kakao", "unregistered", List.of(), LocalDateTime.of(2022, 10, 8, 10, 43, 0, 0));

        given(userRepository.findBySocialLoginId("id")).willReturn(Optional.of(user));
        given(userRepository.findBySocialLoginId("id2")).willReturn(Optional.of(user2));
    }

    @Test
    void informationWithExistUser() {
        assertThat(getUserService.information("id").nickname()).isEqualTo("또또누나");
        assertThat(getUserService.information("id").state()).isEqualTo("unregistered");
        assertThat(getUserService.information("id2").nickname()).isEqualTo("오예오예");
    }

    @Test
    void informationWithNotExistUser() {
        assertThrows(UserNotFoundException.class, () -> {
            getUserService.information("idid");
        });
    }
}
