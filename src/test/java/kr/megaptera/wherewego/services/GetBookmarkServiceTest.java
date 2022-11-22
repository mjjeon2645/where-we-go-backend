package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

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

    //TODO. 보강 필요
//    @Test
    void toggleBookmarkWithValidUser() {
        assertThat(getBookmarkService.toggle(1L, "tester")).hasSize(0);
        assertThat(getBookmarkService.toggle(5L, "tester")).hasSize(1);
    }

    @Test
    void toggleBookmarkWithInvalidUser() {
        assertThrows(UserNotFoundException.class, () -> {
            getBookmarkService.toggle(1L, "");
        });
    }
}
