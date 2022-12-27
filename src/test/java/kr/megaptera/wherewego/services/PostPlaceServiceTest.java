package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PostPlaceServiceTest {
    private PostPlaceService postPlaceService;
    private PlaceRepository placeRepository;
    private AdminRepository adminRepository;

    @BeforeEach
    void setUp() {
        placeRepository = mock(PlaceRepository.class);
        adminRepository = mock(AdminRepository.class);

        postPlaceService = new PostPlaceService(placeRepository, adminRepository);

        Admin foundAdmin = Admin.fake("socialLoginId");

        given(adminRepository.findBySocialLoginId("socialLoginId"))
            .willReturn(Optional.of(foundAdmin));

        given(adminRepository.findBySocialLoginId("xxx"))
            .willThrow(AuthenticationError.class);
    }

    @Test
    void create() {
        PlaceRequestDto placeRequestDto = new PlaceRequestDto("과천 서울랜드",
            "경기 과천시 블라블라", "경기", "과천시", "자연", "012-345", "homepage",
            "possible", "possible", "impossible", "unchecked", "10:00", "20:00",
            "09:00", "22:00", 127.12D, 32.12D, "firstImage", "secondImage", "thirdImage");

        postPlaceService.create(placeRequestDto, "socialLoginId");

        verify(placeRepository).save(any());
    }

    @Test
    void thereAreNoLongitudeAndLatitude() {
        PlaceRequestDto placeRequestDto = new PlaceRequestDto("과천 서울랜드",
            "경기 과천시 블라블라", "경기", "과천시", "자연", "012-345", "homepage",
            "possible", "possible", "impossible", "unchecked", "10:00", "20:00",
            "09:00", "22:00", 0.0D, 0.0D, "firstImage", "secondImage", "thirdImage");

        assertThrows(AddressMissingException.class, () -> {
            postPlaceService.create(placeRequestDto, "socialLoginId");
        });
    }

    @Test
    void thereIsNoCategory() {
        PlaceRequestDto placeRequestDto = new PlaceRequestDto("과천 서울랜드",
            "경기 과천시 블라블라", "경기", "과천시", "select", "012-345", "homepage",
            "possible", "possible", "impossible", "unchecked", "10:00", "20:00",
            "09:00", "22:00", 127.12D, 32.12D, "firstImage", "secondImage", "thirdImage");

        assertThrows(CategoryMissingException.class, () -> {
            postPlaceService.create(placeRequestDto, "socialLoginId");
        });
    }
}
