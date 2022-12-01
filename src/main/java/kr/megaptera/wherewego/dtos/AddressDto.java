package kr.megaptera.wherewego.dtos;

public class AddressDto {
//    private Long placeId;

    private String fullAddress;

    private String sido;

    private String sigungu;

    public AddressDto() {
    }

//    public AddressDto(Long placeId, String fullAddress, String sido, String sigungu) {
//        this.placeId = placeId;
//        this.fullAddress = fullAddress;
//        this.sido = sido;
//        this.sigungu = sigungu;
//    }

    public AddressDto(String fullAddress, String sido, String sigungu) {
        this.fullAddress = fullAddress;
        this.sido = sido;
        this.sigungu = sigungu;
    }

//    public Long getPlaceId() {
//        return placeId;
//    }

    public String getFullAddress() {
        return fullAddress;
    }

    public String getSido() {
        return sido;
    }

    public String getSigungu() {
        return sigungu;
    }
}
