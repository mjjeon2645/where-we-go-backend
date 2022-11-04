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

  public String firstImage() {
    return firstImage;
  }

  public String secondImage() {
    return secondImage;
  }

  public String thirdImage() {
    return thirdImage;
  }

  public Long placeId() {
    return placeId;
  }

  @Override
  public boolean equals(Object other) {
    ImageSource otherImageSource = (ImageSource) other;

    return firstImage.equals(otherImageSource.firstImage())
        && secondImage.equals(otherImageSource.secondImage())
        && thirdImage.equals(otherImageSource.thirdImage())
        && placeId.equals(otherImageSource.placeId());
  }
}
