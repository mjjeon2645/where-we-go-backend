package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.data.domain.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetPlaceServiceTest {
    private PlaceRepository placeRepository;
    private AdminRepository adminRepository;
    private GetPlaceService getPlaceService;

    @BeforeEach
    void setUp() {
        placeRepository = mock(PlaceRepository.class);
        adminRepository = mock(AdminRepository.class);

        getPlaceService =
            new GetPlaceService(placeRepository, adminRepository);

        Admin foundAdmin = Admin.fake("socialLoginId");

        given(adminRepository.findBySocialLoginId("socialLoginId"))
            .willReturn(Optional.of(foundAdmin));

        given(adminRepository.findBySocialLoginId("xxx"))
            .willThrow(AuthenticationError.class);

        given(placeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))).willReturn(List.of(
            Place.fake1(10L, "자연"),
            Place.fake2(11L, "스포츠/레저")
        ));

        Place foundPlace = Place.fake1(3L, "자연");
        given(placeRepository.findById(3L)).willReturn(Optional.of(foundPlace));

        given(placeRepository.findById(5L)).willThrow(PlaceNotFoundException.class);
    }

    @Test
    void places() {
        assertThat(getPlaceService.places("socialLoginId")).hasSize(2);

        verify(adminRepository).findBySocialLoginId("socialLoginId");
        verify(placeRepository).findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Test
    void placesWithWrongSocialLoginId() {
        assertThrows(AuthenticationError.class, () -> {
            getPlaceService.places("xxx");
        });
    }

    @Test
    void selectedPlace() {
        assertThat(getPlaceService.selectedPlace(3L, "socialLoginId").placeServices().nursingRoom())
            .isEqualTo("possible");

        verify(adminRepository).findBySocialLoginId("socialLoginId");
        verify(placeRepository).findById(3L);
    }
}
