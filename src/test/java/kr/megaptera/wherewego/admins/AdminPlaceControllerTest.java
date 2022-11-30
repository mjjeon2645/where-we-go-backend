package kr.megaptera.wherewego.admins;

import kr.megaptera.wherewego.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminPlaceController.class)
class AdminPlaceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPlaceService getPlaceService;

    @Test
    void places() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin-places"))
            .andExpect(status().isOk());
    }
}
