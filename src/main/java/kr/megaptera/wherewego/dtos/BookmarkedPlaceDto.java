package kr.megaptera.wherewego.dtos;

public class BookmarkedPlaceDto {
    private Long placeId;

    private String name;

    private String address;

    public BookmarkedPlaceDto() {
    }

    public BookmarkedPlaceDto(Long placeId, String name, String address) {
        this.placeId = placeId;
        this.name = name;
        this.address = address;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
