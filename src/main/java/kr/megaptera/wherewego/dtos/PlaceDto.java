package kr.megaptera.wherewego.dtos;

public class PlaceDto {
    private Long placeId;

    private String name;

    private PositionDto position;

    private AddressDto address;

    private String category;

    private BusinessHoursDto businessHours;

    private ImageSourceDto imageSource;

    private PlaceServicesDto placeServices;

    private ContactDto contact;

    public PlaceDto() {
    }

    public PlaceDto(Long placeId, String name, PositionDto position, AddressDto address,
                    String category, BusinessHoursDto businessHours, ImageSourceDto imageSource,
                    PlaceServicesDto placeServices, ContactDto contact) {
        this.placeId = placeId;
        this.name = name;
        this.position = position;
        this.address = address;
        this.category = category;
        this.businessHours = businessHours;
        this.imageSource = imageSource;
        this.placeServices = placeServices;
        this.contact = contact;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public String getName() {
        return name;
    }

    public PositionDto getPosition() {
        return position;
    }

    public AddressDto getAddress() {
        return address;
    }

    public String getCategory() {
        return category;
    }

    public BusinessHoursDto getBusinessHours() {
        return businessHours;
    }

    public ImageSourceDto getImageSource() {
        return imageSource;
    }

    public PlaceServicesDto getPlaceServices() {
        return placeServices;
    }

    public ContactDto getContact() {
        return contact;
    }

    @Override
    public String toString() {
        return "placeId: " + placeId + " name: " + name + " position: " + position +
            " Address" + address.toString() + " " + "category: " + category +
            " BusinessHours: " + businessHours.toString() + " imageSource: " + imageSource.toString()
            + " PlaceServices: " + placeServices.toString() + "contact: " + contact.toString();
    }
}
