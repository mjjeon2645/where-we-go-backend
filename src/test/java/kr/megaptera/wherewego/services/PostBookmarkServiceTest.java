//package kr.megaptera.wherewego.services;
//
//import kr.megaptera.wherewego.dtos.*;
//import kr.megaptera.wherewego.models.*;
//import kr.megaptera.wherewego.repositories.*;
//import org.junit.jupiter.api.*;
//
//import java.util.*;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.mockito.BDDMockito.*;
//
//class PostBookmarkServiceTest {
//    private PostBookmarkService postBookmarkService;
//    private UserRepository userRepository;
//    private PlaceRepository placeRepository;
//
//    @BeforeEach
//    void setUp() {
//        userRepository = mock(UserRepository.class);
//        placeRepository = mock(PlaceRepository.class);
//        postBookmarkService = new PostBookmarkService(userRepository, placeRepository);
//
//        List<Bookmark> emptyList = new ArrayList<>();
//        List<Bookmark> bookmarks = new ArrayList<>();
//        bookmarks.add(new Bookmark(3L));
//
//        given(userRepository.findBySocialLoginId("socialId"))
//            .willReturn(Optional.of(new User(1L, "pw", "tester", "email", "socialId", "kakao",
//                "registered", emptyList)));
//
//        given(userRepository.findBySocialLoginId("socialId2"))
//            .willReturn(Optional.of(new User(1L, "pw", "tester", "email", "socialId2", "kakao",
//                "registered", bookmarks)));
//
//        given(placeRepository.findById(3L))
//            .willReturn(Optional.of(new Place(3L, "서울숲 공원", new Position(),
//                new Address(3L, "address", "sido", "sigungu"),
//                "자연", new BusinessHours(), new ImageSource(), new PlaceServices(), new Contact())));
//    }
//
//    @Test
//    void toggle() {
//        assertThat(postBookmarkService.toggle(3L, "socialId"))
//            .isEqualTo(List.of(new BookmarkedPlaceDto(3L, "서울숲 공원", "address")));
//
//        assertThat(postBookmarkService.toggle(3L, "socialId"))
//            .isEqualTo(List.of());
//    }
//}
