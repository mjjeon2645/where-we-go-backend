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

    public PostPlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public Place create(PlaceRequestDto placeRequestDto) {
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

        // 서버측에서 처리하는 예외처리 1. 주소 미입력으로 인해 위도, 경도 없을 시
        if (longitude == 0.0) {
            throw new AddressMissingException();
        }

        // 서버측에서 처리하는 예외처리 2. 카테고리 미입력
        if (category == null || category.equals("select")) {
            throw new CategoryMissingException();
        }

        Place createdPlace = new Place(null,
            placeName, new Position(latitude, longitude),
            new Address(fullAddress, sido, sigungu), category,
            new BusinessHours(
                "월요일: " + weekdayStart + "~" + weekdayEnd,
                "화요일: " + weekdayStart + "~" + weekdayEnd,
                "수요일: " + weekdayStart + "~" + weekdayEnd,
                "목요일: " + weekdayStart + "~" + weekdayEnd,
                "금요일: " + weekdayStart + "~" + weekdayEnd,
                "토요일: " + weekendStart + "~" + weekendEnd,
                "일요일: " + weekendStart + "~" + weekendEnd),
            new ImageSource(firstImage, secondImage, thirdImage),
            new PlaceServices(reservation, parking, outsideFood, nursingRoom),
            new Contact(phone, homepage)
        );

        placeRepository.save(createdPlace);

        return createdPlace;
    }
}
