package kr.megaptera.wherewego.dtos;

public class TopThreePlaceDto {
    private Long placeId;

    private String name;

    private AddressDto address;

    private String averageRate;

    public TopThreePlaceDto() {
    }

    public TopThreePlaceDto(Long placeId, String name, AddressDto address,
                            String averageRate) {
        this.placeId = placeId;
        this.name = name;
        this.address = address;
        this.averageRate = averageRate;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public String getName() {
        return name;
    }

    public AddressDto getAddress() {
        return address;
    }

    public String getAverageRate() {
        return averageRate;
    }
}
