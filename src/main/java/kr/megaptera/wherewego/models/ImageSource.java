package kr.megaptera.wherewego.models;

import javax.persistence.*;

@Entity
public class ImageSource {

  @Id
  @GeneratedValue
  private Long id;

  private String first;

  private String second;

  private String third;

  private Long placeId;

  public ImageSource(Long id, String first, String second, String third, Long placeId) {
    this.id = id;
    this.first = first;
    this.second = second;
    this.third = third;
    this.placeId = placeId;
  }

  public Long id() {
    return id;
  }

  public String first() {
    return first;
  }

  public String second() {
    return second;
  }

  public String third() {
    return third;
  }

  public Long placeId() {
    return placeId;
  }
}
