package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteTrialUserServiceTest {
    private DeleteTrialUserService deleteTrialUserService;
    private UserRepository userRepository;
    private ChildRepository childRepository;
    private UserReviewRepository userReviewRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        childRepository = mock(ChildRepository.class);
        userReviewRepository = mock(UserReviewRepository.class);

        deleteTrialUserService =
            new DeleteTrialUserService(userRepository, childRepository,
                userReviewRepository);

        User foundUserToDelete = User.fake1("angel2645@naver.com");

        given(userRepository.findBySocialLoginId("socialLoginId1"))
            .willReturn(Optional.of(foundUserToDelete));

        given(userRepository.findBySocialLoginId("xxx"))
            .willThrow(UserNotFoundException.class);
    }

    @Test
    void deleteTrialUser() {
        deleteTrialUserService.deleteTrialUser("socialLoginId1");

        verify(userRepository).findBySocialLoginId("socialLoginId1");
        verify(childRepository).deleteAllByUserId(1L);
        verify(userReviewRepository).deleteAllByUserId(1L);
        verify(userRepository).deleteById(1L);
    }

    @Test
    void deleteTrialUserWithWrongSocialLoginId() {
        assertThrows(UserNotFoundException.class, () -> {
            deleteTrialUserService.deleteTrialUser("xxx");
        });
    }
}
