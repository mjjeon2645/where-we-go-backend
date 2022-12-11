//package kr.megaptera.wherewego.services;
//
//import kr.megaptera.wherewego.models.*;
//import kr.megaptera.wherewego.repositories.*;
//import kr.megaptera.wherewego.utils.*;
//import org.junit.jupiter.api.*;
//
//import java.util.*;
//
//import static org.mockito.BDDMockito.*;
//
//class GetBlogReviewServiceTest {
//    private GetBlogReviewService getBlogReviewService;
//    private PlaceRepository placeRepository;
//    private NaverBlogUtil naverBlogUtil;
//
//    @BeforeEach
//    void setUp() {
//        placeRepository = mock(PlaceRepository.class);
//        getBlogReviewService = new GetBlogReviewService(placeRepository);
//        naverBlogUtil = new NaverBlogUtil();
//
//        given(placeRepository.findById(1L))
//            .willReturn(Optional.of(Place.fake1(1L, "자연")));
//    }
//    // TODO. 테스트 필요
//}
