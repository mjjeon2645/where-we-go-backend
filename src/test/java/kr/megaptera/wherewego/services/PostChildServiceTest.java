package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PostChildServiceTest {
    private PostChildService postChildService;
    private ChildRepository childRepository;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        childRepository = mock(ChildRepository.class);
        userRepository = mock(UserRepository.class);

        postChildService = new PostChildService(childRepository, userRepository);

        User foundUser = User.fake1("angel2645@naver.com");

        given(userRepository.findBySocialLoginId("socialLoginId1"))
            .willReturn(Optional.of(foundUser));

        given(userRepository.findBySocialLoginId("xxx"))
            .willThrow(UserNotFoundException.class);

        given(childRepository.findAllByUserId(foundUser.id()))
            .willReturn(List.of(
                new Child(10L, 1L, "공주님", "2020-10-20"),
                new Child(11L, 1L, "아직 몰라요", "2023-10-20")
            ));
    }

    @Test
    void create() {
        ChildCreateDto childCreateDto = new ChildCreateDto("2020-10-20", "아직 몰라요");

        assertThat(postChildService.create("socialLoginId1", childCreateDto)).hasSize(2);

        verify(userRepository).findBySocialLoginId("socialLoginId1");
        verify(childRepository).findAllByUserId(1L);
    }

    @Test
    void createWithWrongUserId() {
        ChildCreateDto childCreateDto = new ChildCreateDto("2020-10-20", "아직 몰라요");

        assertThrows(UserNotFoundException.class, () -> {
            postChildService.create("xxx", childCreateDto);
        });
    }
}
