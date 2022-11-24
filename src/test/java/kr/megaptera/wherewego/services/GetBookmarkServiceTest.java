package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class GetBookmarkServiceTest {
    private UserRepository userRepository;
    private GetBookmarkService getBookmarkService;
    private PlaceRepository placeRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        placeRepository = mock(PlaceRepository.class);
        getBookmarkService = new GetBookmarkService(userRepository, placeRepository);

        List<Bookmark> bookmarks = new ArrayList<>();
        bookmarks.add(new Bookmark(1L));

        User user = new User(
            1L, "PW", "email", "nickname", "tester", "authBy", "registered", bookmarks
        );
        given(userRepository.findBySocialLoginId("tester")).willReturn(Optional.of(user));
        given(placeRepository.findById(1L)).willReturn(Optional.of(Place.fake1(1L, "자연")));
    }

    @Test
    void bookmarks() {
        assertThat(getBookmarkService.bookmarks("tester"))
            .isEqualTo(List.of(
                new BookmarkedPlaceDto(1L, "과천 서울랜드", "경기도 과천시 블라블라")
            ));

        verify(userRepository).findBySocialLoginId("tester");
    }
}
