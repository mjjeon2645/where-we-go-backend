package kr.megaptera.wherewego.dtos;

public class PlaceDto {
  private Long placeId;

  private String name;

  private Double latitude;

  private Double longitude;

  private AddressDto address;

  private String category;

  private BusinessHoursDto businessHours;

  private ImageSourceDto imageSource;

  public PlaceDto(Long id, String name, Double latitude, Double longitude,
                  AddressDto addressDto, String category, BusinessHoursDto businessHoursDto,
                  ImageSourceDto imageSourceDto) {
    this.placeId = id;
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
    this.address = addressDto;
    this.category = category;
    this.businessHours = businessHoursDto;
    this.imageSource = imageSourceDto;
  }

  public PlaceDto() {
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

  public AddressDto getAddress() {
    return address;
  }

  public String getCategory() {
    return category;
  }

  public BusinessHoursDto getBusinessHours() {
    return businessHours;
  }

  public ImageSourceDto getImageSource() {
    return imageSource;
  }

  @Override
  public String toString() {
    return "placeId: " + placeId + " name: " + name + " latitude: " + latitude + " longitude: "
        + longitude + " Address" + address.toString() + " " + "category: " + category;
  }
}
