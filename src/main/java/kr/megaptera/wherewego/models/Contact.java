package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;

import javax.persistence.*;

@Embeddable
public class Contact {
    @Column(name = "place_id", insertable = false, updatable = false)
    private Long placeId;

    private String phone;

    private String homepage;

    public Contact() {
    }

    public Contact(Long placeId, String phone, String homepage) {
        this.placeId = placeId;
        this.phone = phone;
        this.homepage = homepage;
    }

    @Column(name = "place_id", insertable = false, updatable = false)
    public Long placeId() {
        return placeId;
    }

    public String phone() {
        return phone;
    }

    public String homepage() {
        return homepage;
    }

    public ContactDto toDto() {
        return new ContactDto(placeId, phone, homepage);
    }

    public static Contact fake() {
        return new Contact(1L, "010-5634-1740", "https://megaptera.kr/");
    }

    @Override
    public String toString() {
        return "placeid: " + placeId + " phone: " + phone + " homepage: " + homepage;
    }

    @Override
    public boolean equals(Object other) {
        Contact otherContact = (Contact) other;

        return placeId.equals(otherContact.placeId())
            && phone.equals(otherContact.phone())
            && homepage.equals(otherContact.homepage());
    }
}
