package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;

import javax.persistence.*;

@Embeddable
public class ImageSource {
  @Column(name = "place_id", insertable = false, updatable = false)
  private Long placeId;

  @Column(name = "first_image")
  private String firstImage;

  @Column(name = "second_image")
  private String secondImage;

  @Column(name = "third_image")
  private String thirdImage;

  public ImageSource() {
  }

  public ImageSource(Long placeId, String firstImage, String secondImage, String thirdImage) {
    this.placeId = placeId;
    this.firstImage = firstImage;
    this.secondImage = secondImage;
    this.thirdImage = thirdImage;
  }

  public Long placeId() {
    return placeId;
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

  @Override
  public boolean equals(Object other) {
    ImageSource otherImageSource = (ImageSource) other;

    return firstImage.equals(otherImageSource.firstImage())
        && secondImage.equals(otherImageSource.secondImage())
        && thirdImage.equals(otherImageSource.thirdImage())
        && placeId.equals(otherImageSource.placeId());
  }

  public ImageSourceDto toDto() {
    return new ImageSourceDto(placeId, firstImage, secondImage, thirdImage);
  }
}
