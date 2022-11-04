package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;

import javax.persistence.*;

@Entity
public class Place {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private Double latitude;

  private Double longitude;

  private String fullAddress;

  private String sido;

  private String sigungu;

  private String category;

  @Embedded
  private BusinessHours businessHours;

  @Embedded
  private ImageSource imageSource;

  public Long id() {
    return id;
  }

  public String name() {
    return name;
  }

  public Double latitude() {
    return latitude;
  }

  public Double longitude() {
    return longitude;
  }

  public String fullAddress() {
    return fullAddress;
  }

  public String sido() {
    return sido;
  }

  public String sigungu() {
    return sigungu;
  }

  public String category() {
    return category;
  }

  public BusinessHours businessHours() {
    return businessHours;
  }

  public ImageSource imageSource() {
    return imageSource;
  }

  public Place() {
  }


  public Place(Long id, String name, Double latitude, Double longitude,
               String fullAddress, String sido, String sigungu, String category,
               BusinessHours businessHours, ImageSource imageSource) {
    this.id = id;
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
    this.fullAddress = fullAddress;
    this.sido = sido;
    this.sigungu = sigungu;
    this.category = category;
    this.businessHours = businessHours;
    this.imageSource = imageSource;
  }

  public PlaceDto toPlaceDto() {
    return new PlaceDto(id, name, latitude, longitude,
        fullAddress(), sido(), sigungu(), category, businessHours.monday(),
        businessHours.tuesday(), businessHours.wednesday(), businessHours.thursday(),
        businessHours.friday(), businessHours.saturday(), businessHours.sunday(),
        imageSource.firstImage(), imageSource.secondImage(), imageSource.thirdImage());
  }

  @Override
  public String toString() {
    return "id: " + id + " name: " + name + " latitude: " + latitude + " longitude: "
        + longitude + " Address" + fullAddress() + " " + sido() + " " +
        sigungu() + " " + "category: " + category;
  }

  @Override
  public boolean equals(Object other) {
    Place otherPlace = (Place) other;

    return id.equals(otherPlace.id())
        && name.equals(otherPlace.name())
        && latitude.equals(otherPlace.latitude())
        && longitude.equals(otherPlace.longitude())
        && fullAddress.equals(otherPlace.fullAddress)
        && sido.equals(otherPlace.sido())
        && sigungu.equals(otherPlace.sigungu())
        && category.equals(otherPlace.category())
        && businessHours.equals(otherPlace.businessHours())
        && imageSource.equals(otherPlace.imageSource());
  }
}
