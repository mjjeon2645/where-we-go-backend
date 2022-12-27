package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.annotations.*;
import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
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

import java.time.*;
import java.util.*;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookmarkController.class)
@MockMvcEncoding
class BookmarkControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetBookmarkService getBookmarkService;

    @MockBean
    private PostBookmarkService postBookmarkService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String socialLoginId;

    @BeforeEach
    void setUp() {
        socialLoginId = "socialLoginId";

        given(getBookmarkService.bookmarks(socialLoginId)).willReturn(List.of(
            new BookmarkedPlaceDto(10L, "서울숲 공원", "서울시 성동구 서울숲"),
            new BookmarkedPlaceDto(14L, "과천 서울랜드", "경기도 과천시 서울랜드")
        ));

        given(getBookmarkService.bookmarks("xxx")).willThrow(UserNotFoundException.class);
    }

    @Test
    void bookmarks() throws Exception {
        String accessToken = jwtUtil.encode(socialLoginId);

        mockMvc.perform(MockMvcRequestBuilders.get("/bookmarks")
            .header("Authorization", "Bearer " + accessToken)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("address\":\"경기도 과천시 서울랜드\"}")
            ));

        verify(getBookmarkService).bookmarks(socialLoginId);
    }

    @Test
    void bookmarksWithWrongSocialLoginId() throws Exception {
        String accessToken = jwtUtil.encode("xxx");

        mockMvc.perform(MockMvcRequestBuilders.get("/bookmarks")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("사용자 정보를 찾을 수 없습니다")
            ));
    }

    @Test
    void toggleBookmark() throws Exception {
        Long placeId = 3L;

        List<Bookmark> bookmarks = List.of(
            new Bookmark(2L),
            new Bookmark(5L)
        );

        User user = new User(1L, "encodedPassword", "email", "nickname", socialLoginId,
            "kakao", "registered", bookmarks, LocalDateTime.of(2022, 12, 8, 10, 43, 0, 0));

        String accessToken = jwtUtil.encode(socialLoginId);

        mockMvc.perform(MockMvcRequestBuilders.post("/bookmarks/3")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());

        verify(postBookmarkService).toggle(placeId, socialLoginId);
    }

    @Test
    void toggleBookmarkWithInvalidAccessToken() throws Exception {
        String socialLoginId = "xxx";

        List<Bookmark> bookmarks = List.of(
            new Bookmark(2L)
        );

        User user = new User(1L, "encodedPassword", "email", "nickname", socialLoginId,
            "kakao", "registered", bookmarks, LocalDateTime.of(2022, 12, 8, 10, 43, 0, 0));

        mockMvc.perform(MockMvcRequestBuilders.post("/bookmarks/5")
                .header("Authorization", "Bearer ")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }
}
