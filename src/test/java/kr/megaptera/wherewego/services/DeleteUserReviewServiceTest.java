package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import static org.mockito.Mockito.*;

class DeleteUserReviewServiceTest {
    private DeleteUserReviewService deleteUserReviewService;
    private UserReviewRepository userReviewRepository;

    @BeforeEach
    void setUp() {
        userReviewRepository = mock(UserReviewRepository.class);
        deleteUserReviewService = new DeleteUserReviewService(userReviewRepository);
    }

    @Test
    void delete() {
        Long reviewId = 2L;
        deleteUserReviewService.delete(reviewId);

        verify(userReviewRepository).deleteById(2L);
    }
}
