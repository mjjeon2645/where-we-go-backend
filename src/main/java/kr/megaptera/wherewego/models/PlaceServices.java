package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;

import javax.persistence.*;

@Embeddable
public class PlaceServices {
    private String reservation;

    private String parking;

    private String outsideFood;

    private String nursingRoom;

    public PlaceServices() {
    }

    public PlaceServices(String reservation, String parking,
                         String outsideFood, String nursingRoom) {
        this.reservation = reservation;
        this.parking = parking;
        this.outsideFood = outsideFood;
        this.nursingRoom = nursingRoom;
    }

    public static PlaceServices fake() {
        return new PlaceServices("possible", "possible", "impossible", "unchecked");
    }

    public String reservation() {
        return reservation;
    }

    public String parking() {
        return parking;
    }

    public String outsideFood() {
        return outsideFood;
    }

    public String nursingRoom() {
        return nursingRoom;
    }

    public PlaceServicesDto toDto() {
        return new PlaceServicesDto(reservation, parking, outsideFood, nursingRoom);
    }

    @Override
    public String toString() {
        return " reservation: " + reservation +
            " parking: " + parking + " outsideFood" + outsideFood +
            " nursingRoom: " + nursingRoom;
    }

    @Override
    public boolean equals(Object other) {
        PlaceServices otherServices = (PlaceServices) other;

        return reservation.equals(otherServices.reservation())
            && parking.equals(otherServices.parking())
            && outsideFood.equals(otherServices.outsideFood())
            && nursingRoom.equals(otherServices.nursingRoom());
    }
}
