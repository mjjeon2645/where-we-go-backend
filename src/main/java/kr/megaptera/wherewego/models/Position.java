package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;

import javax.persistence.*;

@Embeddable
public class Position {
    @Column(name = "place_id", insertable = false, updatable = false)
    private Long placeId;

    private Double latitude;

    private Double longitude;

    public Position() {
    }

    public Position(Long placeId, Double latitude, Double longitude) {
        this.placeId = placeId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public PositionDto toDto() {
        return new PositionDto(placeId, latitude, longitude);
    }

    public static Position fake() {
        return new Position(1L, 37.434156D, 127.020126D);
    }

    public Long placeId() {
        return placeId;
    }

    public Double latitude() {
        return latitude;
    }

    public Double longitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "placeId: " + placeId + " latitude: " + latitude + " longitude: " + longitude;
    }

    @Override
    public boolean equals(Object other) {
        Position otherPosition = (Position) other;

        return placeId.equals(otherPosition.placeId())
            && latitude.equals(otherPosition.latitude())
            && longitude.equals(otherPosition.longitude());
    }
}
