package kr.megaptera.wherewego.admins;

import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminPlaceController.class)
class AdminPlaceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPlaceService getPlaceService;

    @BeforeEach
    void setUp() {
        given(getPlaceService.selectedPlace(5L)).willReturn(Place.fake1(5L, "자연"));
    }

    @Test
    void places() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin-places"))
            .andExpect(status().isOk());
    }

    @Test
    void selectedPlace() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin-places/5"))
            .andExpect(status().isOk());
    }
}
