package kr.megaptera.wherewego.admins;

import kr.megaptera.wherewego.annotations.*;
import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
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

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminUserController.class)
@MockMvcEncoding
class AdminUserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetUserService getUserService;

    @MockBean
    private GetBookmarkService getBookmarkService;

    @MockBean
    private GetChildService getChildService;

    @MockBean
    private DeleteUserService deleteUserService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String socialLoginId;

    @BeforeEach
    void setUp() {
        socialLoginId = "socialLoginId";

        given(getUserService.users(socialLoginId)).willReturn(List.of(
            User.fake1("angel2645@naver.com")
        ));

        given(getUserService.users("xxx")).willThrow(AuthenticationError.class);

        given(getUserService.user(1L, socialLoginId))
            .willReturn(User.fake1("angel2645@naver.com"));

        given(getBookmarkService.bookmarks(1L))
            .willReturn(List.of(
                new BookmarkedPlaceDto(3L, "과천 서울랜드", "경기도 과천시 블라블라~")
            ));

        given(getChildService.children(1L))
            .willReturn(List.of(
                new ChildDto(7L, 1L, "공주님", "2020-01-01"),
                new ChildDto(9L, 1L, "아직 몰라요", "2023-06-06")
            ));

        given(getUserService.user(1L, "xxx"))
            .willThrow(AuthenticationError.class);

        given(getUserService.user(2L, socialLoginId))
            .willThrow(UserNotFoundException.class);
    }

    @Test
    void users() throws Exception {
        String accessToken = jwtUtil.encode(socialLoginId);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin-users")
            .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("angel2645@naver.com")
            ));

        verify(getUserService).users(socialLoginId);
    }

    @Test
    void usersWithWrongSocialLoginId() throws Exception {
        String accessToken = jwtUtil.encode("xxx");

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-users")
            .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("접근 권한이 없습니다")
            ));
    }

    @Test
    void user() throws Exception {
        String accessToken = jwtUtil.encode(socialLoginId);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-users/1")
            .header("Authorization", "Bearer " + accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("2023-06-06")
            ));

        verify(getUserService).user(1L, socialLoginId);
    }

    @Test
    void userWithWrongSocialLoginId() throws Exception {
        String accessToken = jwtUtil.encode("xxx");

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-users/1")
            .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("접근 권한이 없습니다")
            ));
    }

    @Test
    void findUserWithWrongUserId() throws Exception {
        String accessToken = jwtUtil.encode(socialLoginId);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-users/2")
            .header("Authorization", "Bearer " + accessToken)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("사용자 정보를 찾을 수 없습니다")
            ));
    }

    @Test
    void deleteSelectedUserAndCreatedLog() throws Exception {
        given(deleteUserService.deleteUser(eq(10L), eq(socialLoginId), any()))
            .willReturn(new AdminLog(20L, 100L, new Event(202L, "deleteUser"), "회원 요청에 따른 삭제",
                LocalDateTime.of(2022, 12, 8, 10, 43, 0, 0)));

        String accessToken = jwtUtil.encode(socialLoginId);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin-users/10")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"reason\":\"회원 요청에 따른 삭제\", \"password\":\"Tester123!\"}"))
            .andExpect(status().isCreated())
            .andExpect(content().string(
                containsString("deleteUser")
            ));

        verify(deleteUserService).deleteUser(eq(10L), eq(socialLoginId), any());
    }
}
