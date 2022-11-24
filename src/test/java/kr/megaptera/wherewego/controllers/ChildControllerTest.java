package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.services.*;
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

    @BeforeEach
    void setUp() {
        given(getChildService.children(3L)).willReturn(List.of(
            new ChildDto(1L, 3L, "공주님", "2022-01-01"),
            new ChildDto(2L, 3L, "왕자님", "2020-03-31")
        ));

        ChildCreateDto childCreateDto = new ChildCreateDto("2022-01-05", "공주님");

        given(postChildService.create(4L, childCreateDto))
            .willReturn(List.of(
                new ChildDto(3L, 4L, "공주님", "2022-01-05")
            ));
    }

    @Test
    void children() throws Exception {
        given(getChildService.children(3L)).willReturn(List.of(
            new ChildDto(1L, 3L, "공주님", "2022-01-01"),
            new ChildDto(2L, 3L, "왕자님", "2020-03-31")
        ));

        mockMvc.perform(MockMvcRequestBuilders.get("/children/3"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"birthday\":\"2022-01-01\"")
            ));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/children/4")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"birthday\":\"2022-01-05\",\"gender\":\"공주님\"}"))
            .andExpect(status().isCreated());

        verify(postChildService).create(eq(4L), any());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform((MockMvcRequestBuilders.delete("/children/5"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"childId\":5}"))
            .andExpect(status().isNoContent());
    }
}
