package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional
public class PostPlaceService {
    private final PlaceRepository placeRepository;
    private final AdminRepository adminRepository;

    public PostPlaceService(PlaceRepository placeRepository,
                            AdminRepository adminRepository) {
        this.placeRepository = placeRepository;
        this.adminRepository = adminRepository;
    }

    public Place create(PlaceRequestDto placeRequestDto, String socialLoginId) {
        Admin found = adminRepository.findBySocialLoginId(socialLoginId)
            .orElseThrow(AuthenticationError::new);

        String placeName = placeRequestDto.getPlaceName();

        String fullAddress = placeRequestDto.getFullAddress();
        String sido = placeRequestDto.getSido();
        String sigungu = placeRequestDto.getSigungu();

        String category = placeRequestDto.getCategory();

        String phone = placeRequestDto.getPhone();
        String homepage = placeRequestDto.getHomepage();

        String parking = placeRequestDto.getParking();
        String reservation = placeRequestDto.getReservation();
        String outsideFood = placeRequestDto.getOutsideFood();
        String nursingRoom = placeRequestDto.getNursingRoom();

        String weekdayStart = placeRequestDto.getWeekdayStart();
        String weekdayEnd = placeRequestDto.getWeekdayEnd();
        String weekendStart = placeRequestDto.getWeekendStart();
        String weekendEnd = placeRequestDto.getWeekendEnd();

        Double longitude = placeRequestDto.getLongitude();
        Double latitude = placeRequestDto.getLatitude();

        String firstImage = placeRequestDto.getFirstImage();
        String secondImage = placeRequestDto.getSecondImage();
        String thirdImage = placeRequestDto.getThirdImage();

        // ??????????????? ???????????? ???????????? 1. ?????? ??????????????? ?????? ??????, ?????? ?????? ???
        if (longitude == 0.0) {
            throw new AddressMissingException();
        }

        // ??????????????? ???????????? ???????????? 2. ???????????? ?????????
        if (category == null || category.equals("select")) {
            throw new CategoryMissingException();
        }

        Place createdPlace = new Place(null,
            placeName, new Position(latitude, longitude),
            new Address(fullAddress, sido, sigungu), category,
            new BusinessHours(
                "?????????: " + weekdayStart + "~" + weekdayEnd,
                "?????????: " + weekdayStart + "~" + weekdayEnd,
                "?????????: " + weekdayStart + "~" + weekdayEnd,
                "?????????: " + weekdayStart + "~" + weekdayEnd,
                "?????????: " + weekdayStart + "~" + weekdayEnd,
                "?????????: " + weekendStart + "~" + weekendEnd,
                "?????????: " + weekendStart + "~" + weekendEnd),
            new ImageSource(firstImage, secondImage, thirdImage),
            new PlaceServices(reservation, parking, outsideFood, nursingRoom),
            new Contact(phone, homepage)
        );

        placeRepository.save(createdPlace);

        return createdPlace;
    }
}
