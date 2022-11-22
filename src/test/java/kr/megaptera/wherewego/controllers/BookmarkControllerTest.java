package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import kr.megaptera.wherewego.services.*;
import kr.megaptera.wherewego.utils.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookmarkController.class)
class BookmarkControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetBookmarkService getBookmarkService;

    @MockBean
    private UserRepository userRepository;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void toggleBookmark() throws Exception {
        Long placeId = 1L;
        String socialLoginId = "tester";

        List<Bookmark> bookmarks = List.of(
            new Bookmark(2L)
        );

        User user = new User(1L, "password", "email", "nickname", "tester", "authBy", "registered", bookmarks);

        given(userRepository.findBySocialLoginId(socialLoginId))
            .willReturn(Optional.of(user));

        String accessToken = jwtUtil.encode(socialLoginId);

        mockMvc.perform(MockMvcRequestBuilders.post("/bookmarks/1")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());

        verify(getBookmarkService).toggle(placeId, socialLoginId);
    }

    @Test
    void toggleBookmarkWithInvalidAccessToken() throws Exception {
        Long placeId = 1L;
        String socialLoginId = "tester";

        List<Bookmark> bookmarks = List.of(
            new Bookmark(2L)
        );

        User user = new User(1L, "password", "email", "nickname", "tester", "authBy", "registered", bookmarks);

        given(userRepository.findBySocialLoginId(socialLoginId))
            .willReturn(Optional.of(user));

        mockMvc.perform(MockMvcRequestBuilders.post("/bookmarks/1")
                .header("Authorization", "Bearer ")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }
}
