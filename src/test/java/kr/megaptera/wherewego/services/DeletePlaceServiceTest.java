//package kr.megaptera.wherewego.services;
//
//import kr.megaptera.wherewego.repositories.*;
//import org.junit.jupiter.api.*;
//
//import java.util.*;
//
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.mock;
//
//class DeletePlaceServiceTest {
//    private UserRepository userRepository;
//    private PlaceRepository placeRepository;
//    private UserReviewRepository userReviewRepository;
//    private DeletePlaceService deletePlaceService;
//
//    @BeforeEach
//    void setUp() {
//        userRepository = mock(UserRepository.class);
//        placeRepository = mock(PlaceRepository.class);
//        userReviewRepository = mock(UserReviewRepository.class);
//        deletePlaceService = new DeletePlaceService(userRepository, placeRepository, userReviewRepository, adminRepository);
//
//        given(placeRepository.findAll()).willReturn(List.of(
//            //TODO. 테스트
//        ));
//    }
//
//    @Test
//    void delete() {
//        deletePlaceService.delete(3L, socialLoginId);
//    }
//}
