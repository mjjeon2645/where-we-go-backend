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

  @Embedded
  private Address address;

  private String category;

  @Embedded
  private BusinessHours businessHours;

  @Embedded
  private ImageSource imageSource;

  public Place() {
  }

  public Place(Long id, String name, Double latitude, Double longitude,
               Address address, String category, BusinessHours businessHours,
               ImageSource imageSource) {
    this.id = id;
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
    this.address = address;
    this.category = category;
    this.businessHours = businessHours;
    this.imageSource = imageSource;
  }

  public PlaceDto toPlaceDto() {
    return new PlaceDto(id, name, latitude, longitude,
        address.toDto(), category, businessHours.toDto(), imageSource.toDto());
  }

  public Place fake() {
    Address address = new Address();
    BusinessHours businessHours = new BusinessHours();

    ImageSource imageSource = new ImageSource();

    return new Place(1L, "과천 서울랜드", 37.434156D, 127.020126D, address.fake(),
        "자연", businessHours.fake(), imageSource.fake());
  }

  @Override
  public String toString() {
    return "id: " + id + " name: " + name + " latitude: " + latitude + " longitude: "
        + longitude + " Address" + address() + " " + "category: " + category;
  }

  @Override
  public boolean equals(Object other) {
    Place otherPlace = (Place) other;

    return id.equals(otherPlace.id())
        && name.equals(otherPlace.name())
        && latitude.equals(otherPlace.latitude())
        && longitude.equals(otherPlace.longitude())
        && address.equals(otherPlace.address)
        && category.equals(otherPlace.category())
        && businessHours.equals(otherPlace.businessHours())
        && imageSource.equals(otherPlace.imageSource());
  }

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

  public Address address() {
    return address;
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
}
