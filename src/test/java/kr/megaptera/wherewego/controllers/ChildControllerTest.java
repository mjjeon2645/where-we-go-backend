package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChildController.class)
class ChildControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetChildService getChildService;

    @Test
    void children() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/children/3"))
            .andExpect(status().isOk());
    }
}
