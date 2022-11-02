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

  private Address address;

  private String category;

  private BusinessHours businessHours;

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

  public Place(Long id, String name, Double latitude, Double longitude, Address address, String category, BusinessHours businessHours, ImageSource imageSource) {
    this.id = id;
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
    this.address = address;
    this.category = category;
    this.businessHours = businessHours;
    this.imageSource = imageSource;
  }

  public PositionDto toPositionDto() {
    return new PositionDto(id, name, latitude, longitude);
  }
}
