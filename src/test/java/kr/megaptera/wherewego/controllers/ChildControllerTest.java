package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import kr.megaptera.wherewego.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import java.util.*;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChildController.class)
class ChildControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetChildService getChildService;

    @MockBean
    private ChildRepository childRepository;

    @BeforeEach
    void setUp() {
        given(getChildService.children(3L)).willReturn(List.of(
            new ChildDto(1L, 3L, "공주님", "2022-01-01"),
            new ChildDto(2L, 3L, "왕자님", "2020-03-31")
        ));

        ChildCreateDto childCreateDto = new ChildCreateDto("2022-01-05", "공주님");

        given(getChildService.create(4L, childCreateDto)).willReturn(List.of(
            new ChildDto(3L, 4L, "공주님", "2022-01-05")
        ));
    }

    @Test
    void children() throws Exception {
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
            .andExpect(status().isCreated())
            .andExpect(content().string(
                containsString("\"birthday\"")
            ));

        verify(childRepository).save(any());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform((MockMvcRequestBuilders.patch("/children/5"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"childId\":5}"))
            .andExpect(status().isNoContent());
        verify(childRepository).deleteById(any());
    }
}
