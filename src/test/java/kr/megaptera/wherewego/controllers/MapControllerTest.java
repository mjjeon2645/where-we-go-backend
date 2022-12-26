package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.annotations.*;
import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import java.util.*;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MapController.class)
@MockMvcEncoding
class MapControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetMapService getMapService;

    @BeforeEach
    void setUp() {
     given(getMapService.filteredPlaces("전체", "전체", "전체")).willReturn(List.of(
        Place.fake1(1L, "자연"),
         Place.fake2(2L, "스포츠/레저")
     ));

        given(getMapService.filteredPlaces("서울", "성동구", "스포츠/레저")).willReturn(List.of(
            Place.fake2(2L, "스포츠/레저")
        ));

        given(getMapService.filteredPlaces("서울", "선택", "스포츠/레저"))
            .willThrow(RegionNotSelected.class);

        given(getMapService.filteredPlaces("서울", "성동구", "선택"))
            .willThrow(CategoryNotSelected.class);
    }

    @Test
    void places() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/map")
                .param("sido", "")
                .param("sigungu", "")
                .param("category", ""))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("과천 서울랜드")
            ));

        verify(getMapService).filteredPlaces("전체", "전체", "전체");
    }

    @Test
    void placesWithConditions() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/map")
            .param("sido", "서울")
            .param("sigungu", "성동구")
            .param("category", "스포츠/레저"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("서울숲 공원")
            ));

        verify(getMapService).filteredPlaces("서울", "성동구", "스포츠/레저");
    }

    @Test
    void placesWithNoRegion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/map")
            .param("sido", "서울")
            .param("sigungu", "선택")
            .param("category", "스포츠/레저"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("가고싶은 지역을 선택해주세요!")
            ));
    }

    @Test
    void placesWithNoCategory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/map")
                .param("sido", "서울")
                .param("sigungu", "성동구")
                .param("category", "선택"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("가고싶은 장소의 유형을 선택해주세요!")
            ));
    }
}
