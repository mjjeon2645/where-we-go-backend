package kr.megaptera.wherewego.dtos;

public class PositionDto {
  private Long placeId;

  private String name;

  private Double latitude;

  private Double longitude;

  public PositionDto() {
  }

  public PositionDto(Long placeId, String name, Double latitude, Double longitude) {
    this.placeId = placeId;
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public Long getPlaceId() {
    return placeId;
  }

  public String getName() {
    return name;
  }

  public Double getLatitude() {
    return latitude;
  }

  public Double getLongitude() {
    return longitude;
  }
}
