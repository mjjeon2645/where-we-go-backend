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

  public ImageSource fake() {
    return new ImageSource(
        1L,
        "https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png",
        "https://user-images.githubusercontent.com/104840243/198858244-29a83802-3ebe-42c8-894a-151c0962b8da.png",
        "https://user-images.githubusercontent.com/104840243/198858249-0e5eb65b-1a68-4549-bace-b906aa550413.png"
    );
  }

  public ImageSourceDto toDto() {
    return new ImageSourceDto(placeId, firstImage, secondImage, thirdImage);
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
}
