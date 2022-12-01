package kr.megaptera.wherewego.dtos;

public class ContactDto {
//    private Long placeId;

    private String phone;

    private String homepage;

    public ContactDto() {
    }

    public ContactDto(String phone, String homepage) {
        this.phone = phone;
        this.homepage = homepage;
    }

//    public ContactDto(Long placeId, String phone, String homepage) {
//        this.placeId = placeId;
//        this.phone = phone;
//        this.homepage = homepage;
//    }

//    public Long getPlaceId() {
//        return placeId;
//    }

    public String getPhone() {
        return phone;
    }

    public String getHomepage() {
        return homepage;
    }
}
