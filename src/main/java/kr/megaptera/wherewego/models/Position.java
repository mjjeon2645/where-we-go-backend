package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;

import javax.persistence.*;

@Embeddable
public class Position {
    private Double latitude;

    private Double longitude;

    public Position() {
    }

    public Position(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public PositionDto toDto() {
        return new PositionDto(latitude, longitude);
    }

    public static Position fake() {
        return new Position(37.434156D, 127.020126D);
    }

    public Double latitude() {
        return latitude;
    }

    public Double longitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "latitude: " + latitude + " longitude: " + longitude;
    }

    @Override
    public boolean equals(Object other) {
        Position otherPosition = (Position) other;

        return latitude.equals(otherPosition.latitude())
            && longitude.equals(otherPosition.longitude());
    }
}
