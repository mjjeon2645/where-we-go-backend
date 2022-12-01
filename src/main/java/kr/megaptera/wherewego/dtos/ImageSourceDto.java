package kr.megaptera.wherewego.dtos;

public class ImageSourceDto {
//    private Long placeId;

    private String firstImage;

    private String secondImage;

    private String thirdImage;

    public ImageSourceDto() {
    }

    public ImageSourceDto(String firstImage, String secondImage, String thirdImage) {
        this.firstImage = firstImage;
        this.secondImage = secondImage;
        this.thirdImage = thirdImage;
    }

//    public ImageSourceDto(Long placeId, String firstImage, String secondImage, String thirdImage) {
//        this.placeId = placeId;
//        this.firstImage = firstImage;
//        this.secondImage = secondImage;
//        this.thirdImage = thirdImage;
//    }


//    public Long getPlaceId() {
//        return placeId;
//    }

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
