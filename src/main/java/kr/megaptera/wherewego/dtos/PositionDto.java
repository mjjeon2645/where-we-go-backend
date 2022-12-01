package kr.megaptera.wherewego.dtos;

public class PositionDto {
//    private Long placeId;

    private Double latitude;

    private Double longitude;

    public PositionDto() {
    }

//    public PositionDto(Long placeId, Double latitude, Double longitude) {
//        this.placeId = placeId;
//        this.latitude = latitude;
//        this.longitude = longitude;
//    }

    public PositionDto(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

//    public Long getPlaceId() {
//        return placeId;
//    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
