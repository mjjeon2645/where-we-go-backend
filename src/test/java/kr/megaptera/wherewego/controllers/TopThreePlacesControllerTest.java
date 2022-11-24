package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TopThreePlacesController.class)
class TopThreePlacesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetTopThreePlacesService getTopThreePlacesService;

    @BeforeEach
    void setUp() {
        given(getTopThreePlacesService.topThreePlaces()).willReturn(
            List.of(
                new TopThreePlaceDto(1L, "덕수궁", Address.fake1().toDto(), "4.5"),
                new TopThreePlaceDto(2L, "과천 서울랜드", Address.fake1().toDto(), "3.87"),
                new TopThreePlaceDto(3L, "메가테라", Address.fake1().toDto(), "3.51")
            )
        );
    }

    @Test
    void topThreePlaces() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/top-three-places"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"name\":")
            ));

        verify(getTopThreePlacesService).topThreePlaces();
    }
}
