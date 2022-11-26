package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
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

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChildController.class)
class ChildControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetChildService getChildService;

    @MockBean
    private PostChildService postChildService;

    @MockBean
    private DeleteChildService deleteChildService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String accessToken;

    @BeforeEach
    void setUp() {
        accessToken = jwtUtil.encode("socialLoginId");

        given(getChildService.children("socialLoginId")).willReturn(List.of(
            new ChildDto(1L, 3L, "공주님", "2022-01-01"),
            new ChildDto(2L, 3L, "왕자님", "2020-03-31")
        ));

        ChildCreateDto childCreateDto = new ChildCreateDto("2022-01-05", "공주님");

        given(postChildService.create("socialLoginId2", childCreateDto))
            .willReturn(List.of(
                new ChildDto(3L, 4L, "공주님", "2022-01-05")
            ));
    }

    @Test
    void children() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/children")
                .header("Authorization", "Bearer " + accessToken))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"birthday\":\"2022-01-01\"")
            ));
    }

    @Test
    void create() throws Exception {
        accessToken = jwtUtil.encode("socialLoginId2");

        mockMvc.perform(MockMvcRequestBuilders.post("/children")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"birthday\":\"2022-01-05\",\"gender\":\"공주님\"}"))
            .andExpect(status().isCreated());

        verify(postChildService).create(eq("socialLoginId2"), any());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform((MockMvcRequestBuilders.delete("/children")
                .header("Authorization", "Bearer " + accessToken))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"childId\":5}"))
            .andExpect(status().isNoContent());
    }
}
