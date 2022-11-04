package kr.megaptera.wherewego.dtos;

public class PlaceDto {
  private Long placeId;

  private String name;

  private Double latitude;

  private Double longitude;

  private String fullAddress;

  private String sido;

  private String sigungu;

  private String category;

  private String monday;

  private String tuesday;

  private String wednesday;

  private String thursday;

  private String friday;

  private String saturday;

  private String sunday;

  private String firstImage;

  private String secondImage;

  private String thirdImage;

  public PlaceDto() {
  }

  public PlaceDto(Long placeId, String name, Double latitude, Double longitude,
                  String fullAddress, String sido, String sigungu, String category,
                  String monday, String tuesday, String wednesday, String thursday,
                  String friday, String saturday, String sunday, String firstImage,
                  String secondImage, String thirdImage) {
    this.placeId = placeId;
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
    this.fullAddress = fullAddress;
    this.sido = sido;
    this.sigungu = sigungu;
    this.category = category;
    this.monday = monday;
    this.tuesday = tuesday;
    this.wednesday = wednesday;
    this.thursday = thursday;
    this.friday = friday;
    this.saturday = saturday;
    this.sunday = sunday;
    this.firstImage = firstImage;
    this.secondImage = secondImage;
    this.thirdImage = thirdImage;
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

  public String getMonday() {
    return monday;
  }

  public String getTuesday() {
    return tuesday;
  }

  public String getWednesday() {
    return wednesday;
  }

  public String getThursday() {
    return thursday;
  }

  public String getFriday() {
    return friday;
  }

  public String getSaturday() {
    return saturday;
  }

  public String getSunday() {
    return sunday;
  }

  public String getFirstImage() {
    return firstImage;
  }

  public String getSecondImage() {
    return secondImage;
  }

  public String getThirdImage() {
    return thirdImage;
  }
}
