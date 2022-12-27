package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import java.time.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class GetBookmarkServiceTest {
    private GetBookmarkService getBookmarkService;
    private UserRepository userRepository;
    private PlaceRepository placeRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        placeRepository = mock(PlaceRepository.class);
        getBookmarkService = new GetBookmarkService(userRepository, placeRepository);


        User foundUser = new User(1L, "encodedPassword", "angel2645@naver.com", "민지룽룽",
            "socialLoginId1", "naver", "registered", List.of(new Bookmark(2L)),
            LocalDateTime.of(2022, 10,10,10,10,10,1));

        given(userRepository.findBySocialLoginId("socialLoginId1")).willReturn(Optional.of(foundUser));
        given(userRepository.findById(1L)).willReturn(Optional.of(foundUser));

        Place foundPlace = Place.fake2(2L, "자연");
        given(placeRepository.findById(2L)).willReturn(Optional.of(foundPlace));
    }

    @Test
    void bookmarksWithSocialLoginId() {
        assertThat(getBookmarkService.bookmarks("socialLoginId1"))
            .isEqualTo(List.of(
                new BookmarkedPlaceDto(2L, "서울숲 공원", "서울시 성동구 블라블라")
            ));

        verify(userRepository).findBySocialLoginId("socialLoginId1");
        verify(placeRepository).findById(2L);
    }

    @Test
    void bookmarksWithId() {
        assertThat(getBookmarkService.bookmarks(1L))
            .isEqualTo(List.of(
                new BookmarkedPlaceDto(2L, "서울숲 공원", "서울시 성동구 블라블라")
            ));

        verify(userRepository).findById(1L);
        verify(placeRepository).findById(2L);
    }
}
