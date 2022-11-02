package kr.megaptera.wherewego.models;

import javax.persistence.*;

@Embeddable
public class ImageSource {

  @Column(name = "first_image")
  private String firstImage;

  @Column(name = "second_image")
  private String secondImage;

  @Column(name = "third_image")
  private String thirdImage;

  @Column(name = "place_id", insertable = false, updatable = false)
  private Long placeId;

  public ImageSource() {
  }

  public ImageSource(String firstImage, String secondImage, String thirdImage, Long placeId) {
    this.firstImage = firstImage;
    this.secondImage = secondImage;
    this.thirdImage = thirdImage;
    this.placeId = placeId;
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

  public Long getPlaceId() {
    return placeId;
  }
}
