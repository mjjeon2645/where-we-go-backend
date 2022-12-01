package kr.megaptera.wherewego.dtos;

public class PlaceRequestDto {
    private String placeName;

    private String fullAddress;

    private String sido;

    private String sigungu;

    private String category;

    private String phone;

    private String homepage;

    private String parking;

    private String reservation;

    private String outsideFood;

    private String nursingRoom;

    private String weekdayStart;

    private String weekdayEnd;

    private String weekendStart;

    private String weekendEnd;

    private Double longitude;

    private Double latitude;

    public PlaceRequestDto() {
    }

    public PlaceRequestDto(String placeName, String fullAddress, String sido,
                           String sigungu, String category, String phone,
                           String homepage, String parking, String reservation,
                           String outsideFood, String nursingRoom, String weekdayStart,
                           String weekdayEnd, String weekendStart, String weekendEnd,
                           Double longitude, Double latitude) {
        this.placeName = placeName;
        this.fullAddress = fullAddress;
        this.sido = sido;
        this.sigungu = sigungu;
        this.category = category;
        this.phone = phone;
        this.homepage = homepage;
        this.parking = parking;
        this.reservation = reservation;
        this.outsideFood = outsideFood;
        this.nursingRoom = nursingRoom;
        this.weekdayStart = weekdayStart;
        this.weekdayEnd = weekdayEnd;
        this.weekendStart = weekendStart;
        this.weekendEnd = weekendEnd;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public String getSido() {
        return sido;
    }

    public String getSigungu() {
        return sigungu;
    }

    public String getCategory() {
        return category;
    }

    public String getPhone() {
        return phone;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getParking() {
        return parking;
    }

    public String getReservation() {
        return reservation;
    }

    public String getOutsideFood() {
        return outsideFood;
    }

    public String getNursingRoom() {
        return nursingRoom;
    }

    public String getWeekdayStart() {
        return weekdayStart;
    }

    public String getWeekdayEnd() {
        return weekdayEnd;
    }

    public String getWeekendStart() {
        return weekendStart;
    }

    public String getWeekendEnd() {
        return weekendEnd;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }
}
