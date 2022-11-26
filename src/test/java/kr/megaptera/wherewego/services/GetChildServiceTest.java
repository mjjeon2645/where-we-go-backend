package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

class GetChildServiceTest {
    private ChildRepository childRepository;
    private GetChildService getChildService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        childRepository = mock(ChildRepository.class);
        userRepository = mock(UserRepository.class);
        getChildService = new GetChildService(childRepository, userRepository);

        given(userRepository.findBySocialLoginId("socialLoginId"))
            .willReturn(Optional.of(User.fake("angel2645@naver.com")));

        given(childRepository.findAllByUserId(1L))
            .willReturn(List.of(
                new Child(1L, 1L, "공주님", "2022-01-01"),
                new Child(2L, 1L, "공주님", "2022-01-01")
            ));
    }

    @Test
    void children() {
        assertThat(getChildService.children("socialLoginId")).hasSize(2);
        assertThrows(UserNotFoundException.class, () -> {
            getChildService.children("socialLoginId2");
        });
    }
}
