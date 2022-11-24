package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

class PostUserReviewServiceTest {
    private UserReviewRepository userReviewRepository;
    private UserRepository userRepository;
    private PostUserReviewService postUserReviewService;

    @BeforeEach
    void setUp() {
        userReviewRepository = mock(UserReviewRepository.class);
        userRepository = mock(UserRepository.class);
        postUserReviewService = new PostUserReviewService(
            userReviewRepository, userRepository);

        given(userRepository.findBySocialLoginId("socialLoginId"))
            .willReturn(Optional.of(new User(1L, "EP", "email", "또또누나", "socialLoginId",
                "kakao", "registered", List.of())));
    }

    @Test
    void create() {
        MyReviewDto myReviewDto = new MyReviewDto(1L, "2022-10-23", 5L, "좋았어요!");
        String socialLoginId = "socialLoginId";
        UserReview createdUserReview = postUserReviewService.create(myReviewDto, socialLoginId);

        verify(userReviewRepository).save(any());
    }

}