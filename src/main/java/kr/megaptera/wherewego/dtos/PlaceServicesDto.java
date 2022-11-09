package kr.megaptera.wherewego.dtos;

public class PlaceServicesDto {
    private Long placeId;

    private String reservation;

    private String parking;

    private String outsideFood;

    private String nursingRoom;

    public PlaceServicesDto() {
    }

    public PlaceServicesDto(Long placeId, String reservation, String parking, String outsideFood, String nursingRoom) {
        this.placeId = placeId;
        this.reservation = reservation;
        this.parking = parking;
        this.outsideFood = outsideFood;
        this.nursingRoom = nursingRoom;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public String getReservation() {
        return reservation;
    }

    public String getParking() {
        return parking;
    }

    public String getOutsideFood() {
        return outsideFood;
    }

    public String getNursingRoom() {
        return nursingRoom;
    }
}
