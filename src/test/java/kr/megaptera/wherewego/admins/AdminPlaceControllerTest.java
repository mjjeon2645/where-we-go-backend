package kr.megaptera.wherewego.admins;

import kr.megaptera.wherewego.annotations.*;
import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import kr.megaptera.wherewego.utils.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import java.time.*;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminPlaceController.class)
@MockMvcEncoding
class AdminPlaceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPlaceService getPlaceService;

    @MockBean
    private PostPlaceService postPlaceService;

    @MockBean
    private DeletePlaceService deletePlaceService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String socialLoginId;

    @BeforeEach
    void setUp() {
        socialLoginId = "socialLoginId";

        given(getPlaceService.places(socialLoginId)).willReturn(List.of(
            new Place(1L, "과천 서울랜드", Position.fake(), Address.fake1(), "자연",
                BusinessHours.fake(), ImageSource.fake(), PlaceServices.fake(),
                Contact.fake())
        ));

        given(getPlaceService.places("xxx")).willThrow(AuthenticationError.class);

        given(getPlaceService.selectedPlace(5L, socialLoginId))
            .willReturn(Place.fake1(5L, "스포츠/레저"));

        given(getPlaceService.selectedPlace(5L, "xxx")).willThrow(AuthenticationError.class);

        given(getPlaceService.selectedPlace(7L, socialLoginId))
            .willThrow(PlaceNotFoundException.class);

//        given(postPlaceService.create(new PlaceRequestDto(
//            "과천 서울랜드", "경기도 과천시 블라블라", "경기", "과천시", "자연", "012-345",
//            "http://homepage", "possible", "impossible", "unchecked", "possible",
//            "09:00", "18:00", "09:00", "20:00", 127.234D, 37.123D, "source1",
//            "source2", "source3"
//        ), socialLoginId))
//            .willReturn(Place.fake1(10L, "자연"));
    }

    @Test
    void places() throws Exception {
        String accessToken = jwtUtil.encode(socialLoginId);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-places")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(
                "자연"
            )));

        verify(getPlaceService).places(socialLoginId);
    }

    @Test
    void accessTokenError() throws Exception {
        String accessToken = jwtUtil.encode("xxx");

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-places")
            .header("Authorization", "Bearer " + accessToken)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void selectedPlace() throws Exception {
        String accessToken = jwtUtil.encode(socialLoginId);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-places/5")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(
                "스포츠/레저"
            )));

        verify(getPlaceService).selectedPlace(5L, socialLoginId);
    }

    @Test
    void accessTokenErrorWithSelectedPlace() throws Exception {
        String accessToken = jwtUtil.encode("xxx");

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-places/5")
            .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void thereIsNoIdOfSelectedPlace() throws Exception {
        String accessToken = jwtUtil.encode(socialLoginId);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-places/7")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("장소를 찾을수가 없습니다")
            ));
    }
    @Test
    void addPlace() throws Exception {
        String accessToken = jwtUtil.encode(socialLoginId);

        given(postPlaceService.create(any(PlaceRequestDto.class), eq(socialLoginId)))
            .willReturn(Place.fake1(10L, "자연"));

        mockMvc.perform(MockMvcRequestBuilders.post("/admin-places/new")
            .header("Authorization", "Bearer " + accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"placeName\":\"과천 서울랜드\", \"fullAddress\":\"경기 과천시 블라블라\", " +
                "\"sido\":\"경기\", \"sigungu\":\"과천시\", \"category\":\"자연\", " +
                "\"phone\":\"012-345\", \"homepage\":\"http://homepage\", " +
                "\"parking\":\"possible\", \"reservation\":\"possible\", " +
                "\"outsideFood\":\"possible\", \"nursingRoom\":\"possible\", " +
                "\"weekdayStart\":\"09:00\", \"weekdayEnd\":\"18:00\", " +
                "\"weekendStart\":\"09:00\", \"weekendEnd\":\"20:00\", " +
                "\"longitude\":127.234, \"latitude\":37.123, \"firstImage\":\"source1\", " +
                "\"secondImage\":\"source2\", \"thirdImage\":\"source3\"" +
                "}"))
            .andExpect(status().isCreated())
            .andExpect(content().string(
                containsString("경기도 과천시 블라블라")
            ));

        verify(postPlaceService).create(any(), eq(socialLoginId));
    }

    @Test
    void addPlaceWithNoAddress() throws Exception {
        String accessToken = jwtUtil.encode(socialLoginId);

        given(postPlaceService.create(any(PlaceRequestDto.class), eq(socialLoginId)))
            .willThrow(AddressMissingException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin-places/new")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"placeName\":\"과천 서울랜드\", \"category\":\"자연\", " +
                    "\"phone\":\"012-345\", \"homepage\":\"http://homepage\", " +
                    "\"parking\":\"possible\", \"reservation\":\"possible\", " +
                    "\"outsideFood\":\"possible\", \"nursingRoom\":\"possible\", " +
                    "\"weekdayStart\":\"09:00\", \"weekdayEnd\":\"18:00\", " +
                    "\"weekendStart\":\"09:00\", \"weekendEnd\":\"20:00\", " +
                    "\"firstImage\":\"source1\", " +
                    "\"secondImage\":\"source2\", \"thirdImage\":\"source3\"" +
                    "}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("주소 찾기를 진행해주세요")
            ));
    }

    @Test
    void addPlaceWithNoCategory() throws Exception {
        String accessToken = jwtUtil.encode(socialLoginId);

        given(postPlaceService.create(any(PlaceRequestDto.class), eq(socialLoginId)))
            .willThrow(CategoryMissingException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin-places/new")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"placeName\":\"과천 서울랜드\", \"fullAddress\":\"경기 과천시 블라블라\", " +
                    "\"sido\":\"경기\", \"sigungu\":\"과천시\", " +
                    "\"phone\":\"012-345\", \"homepage\":\"http://homepage\", " +
                    "\"parking\":\"possible\", \"reservation\":\"possible\", " +
                    "\"outsideFood\":\"possible\", \"nursingRoom\":\"possible\", " +
                    "\"weekdayStart\":\"09:00\", \"weekdayEnd\":\"18:00\", " +
                    "\"weekendStart\":\"09:00\", \"weekendEnd\":\"20:00\", " +
                    "\"longitude\":127.234, \"latitude\":37.123, \"firstImage\":\"source1\", " +
                    "\"secondImage\":\"source2\", \"thirdImage\":\"source3\"" +
                    "}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("장소 유형을 선택해주세요")
            ));
    }

    @Test
    void delete() throws Exception {
        given(deletePlaceService.delete(eq(12L), eq(socialLoginId), any(DeletePlaceRequestDto.class)))
            .willReturn(new AdminLog(10L, 23L, new Event(201L, "deletePlace"), "없어진 장소입니다.",
                LocalDateTime.of(2022, 12, 8, 10, 43, 0, 0)));

        String accessToken = jwtUtil.encode(socialLoginId);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin-places/12")
            .header("Authorization", "Bearer " + accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"reason\":\"없어진 장소입니다.\", \"password\":\"Tester123!\"" +
                "}"))
            .andExpect(status().isCreated())
            .andExpect(content().string(
                containsString("deletePlace")
            ));

        verify(deletePlaceService).delete(eq(12L), eq(socialLoginId), any());
    }

    @Test
    void deleteWithWrongPassword() throws Exception {
        given(deletePlaceService.delete(eq(12L), eq(socialLoginId), any(DeletePlaceRequestDto.class)))
            .willThrow(AdminPasswordError.class);

        String accessToken = jwtUtil.encode(socialLoginId);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin-places/12")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"reason\":\"없어진 장소입니다.\", \"password\":\"XXX\"" +
                    "}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("비밀번호가 맞지 않습니다. 다시 확인해주세요")
            ));
    }

    @Test
    void deleteWithNoReason() throws Exception {
        given(deletePlaceService.delete(eq(12L), eq(socialLoginId), any(DeletePlaceRequestDto.class)))
            .willThrow(EmptyReasonException.class);

        String accessToken = jwtUtil.encode(socialLoginId);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin-places/12")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"reason\":\"\", \"password\":\"Tester123!\"" +
                    "}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("사유를 입력해주세요")
            ));
    }
}
