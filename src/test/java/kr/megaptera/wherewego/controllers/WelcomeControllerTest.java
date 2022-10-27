package kr.megaptera.wherewego.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WelcomeController.class)
class WelcomeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void home() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/"))
        .andExpect(status().isOk());
  }
}
