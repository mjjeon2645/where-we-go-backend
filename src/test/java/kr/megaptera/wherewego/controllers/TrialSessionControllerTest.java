package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import kr.megaptera.wherewego.utils.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.*;
import org.springframework.test.context.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import java.time.*;
import java.util.*;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrialSessionController.class)
@ActiveProfiles("test")
class TrialSessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetTrialUserService getTrialUserService;

    @MockBean
    private DeleteTrialUserService deleteTrialUserService;

    @SpyBean
    private JwtUtil jwtUtil;

    @SpyBean
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        String socialLoginId = "trialUserId-" + UUID.randomUUID();

        String trialUserNickname = "테스터" + socialLoginId.split("-")[2];

        List<Bookmark> bookmarks = new ArrayList<>();

        User trialUser = new User(3L, passwordEncoder.encode("trialUserPassword"),
            "tester@tester.com", trialUserNickname, socialLoginId, "admin",
            User.REGISTERED, bookmarks, LocalDateTime.of(2022, 12, 8, 10, 43, 0, 0));

        given(getTrialUserService.trialUser()).willReturn(trialUser);

        String accessToken = jwtUtil.encode(trialUser.socialLoginId());
    }

    @Test
    void trialLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/trial-session"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("registered")
            ));
    }

    @Test
    void deleteTrialUserInformation() throws Exception {
        String accessToken = jwtUtil.encode("trialUserId-1234");

        mockMvc.perform(MockMvcRequestBuilders.delete("/trial-session")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }
}
