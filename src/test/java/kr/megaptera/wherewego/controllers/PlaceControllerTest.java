package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
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
class PlaceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MapService mapService;

//    @MockBean
//    private PlaceRepository placeRepository;

    @BeforeEach
    void setUp() {
        Place place = new Place();

        given(mapService.selectedPlace(1L)).willReturn(place.fake());
    }

    @Test
    void selectedPlace() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/places/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"name\":")
            ));
    }
}
