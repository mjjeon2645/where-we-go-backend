package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;

import javax.persistence.*;

@Entity
public class Place {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private Position position;

    @Embedded
    private Address address;

    private String category;

    @Embedded
    private BusinessHours businessHours;

    @Embedded
    private ImageSource imageSource;

    @Embedded
    private PlaceServices placeServices;

    @Embedded
    private Contact contact;

    public Place() {
    }

    public Place(Long id, String name, Position position, Address address, String category,
                 BusinessHours businessHours, ImageSource imageSource,
                 PlaceServices placeServices, Contact contact) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.address = address;
        this.category = category;
        this.businessHours = businessHours;
        this.imageSource = imageSource;
        this.placeServices = placeServices;
        this.contact = contact;
    }

    public PlaceDto toPlaceDto() {
        return new PlaceDto(id, name, position.toDto(), address.toDto(), category,
            businessHours.toDto(), imageSource.toDto(), placeServices.toDto(),
            contact.toDto());
    }

    public static Place fake1(Long id, String category) {
        return new Place(id, "과천 서울랜드", new Position(37.123D, 127.234D),
            new Address("경기도 과천시 블라블라", "경기", "과천시"),
            category, BusinessHours.fake(),
            new ImageSource("source1", "source2", "source3"),
            new PlaceServices("possible", "possible", "possible", "possible"),
            new Contact("012-345", "https://homepage"));
    }

    public static Place fake2(Long id, String category) {
        return new Place(id, "서울숲 공원", new Position(37D, 120D),
            new Address("서울시 성동구 블라블라", "서울", "성동구"),
            category, new BusinessHours("월", "화", "수", "목", "금", "토", "일"),
            new ImageSource("source1", "source2", "source3"),
            new PlaceServices("impossible", "possible", "possible", "unchecked"),
            new Contact("012-345", "https://~~"));
    }

    @Override
    public String toString() {
        return "id: " + id + " name: " + name + " position: " + position.toString() + " Address" + address.toString() +
            " category: " + category + " businessHours: " + businessHours.toString() +
            " ImageSource: " + imageSource.toString() + " placeService: " + placeServices.toString() +
            " contact: " + contact.toString();
    }

    @Override
    public boolean equals(Object other) {
        Place otherPlace = (Place) other;

        return id.equals(otherPlace.id())
            && name.equals(otherPlace.name())
            && position.equals(otherPlace.position())
            && address.equals(otherPlace.address)
            && category.equals(otherPlace.category())
            && businessHours.equals(otherPlace.businessHours())
            && imageSource.equals(otherPlace.imageSource())
            && placeServices.equals(otherPlace.placeServices())
            && contact.equals(otherPlace.contact());
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Position position() {
        return position;
    }

    public Address address() {
        return address;
    }

    public String category() {
        return category;
    }

    public BusinessHours businessHours() {
        return businessHours;
    }

    public ImageSource imageSource() {
        return imageSource;
    }

    public PlaceServices placeServices() {
        return placeServices;
    }

    public Contact contact() {
        return contact;
    }

    public BookmarkedPlaceDto toBookmarkedPlaceDto() {
        return new BookmarkedPlaceDto(id, name, address.fullAddress());
    }
}
