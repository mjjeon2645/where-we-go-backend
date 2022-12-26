package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.annotations.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlaceController.class)
@MockMvcEncoding
class PlaceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetMapService getMapService;

    @BeforeEach
    void setUp() {
        Place place = new Place();

        given(getMapService.selectedPlace(1L)).willReturn(place.fake1(1L, "자연"));

        given(getMapService.selectedPlace(5L)).willThrow(SelectedPlaceNotFound.class);
    }

    @Test
    void selectedPlace() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/places/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"name\":")
            ));

        verify(getMapService).selectedPlace(1L);
    }

    @Test
    void selectedPlaceWithNoInformationId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/places/5"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("선택한 장소의 정보를 불러오지 못했습니다")
            ));
    }
}
