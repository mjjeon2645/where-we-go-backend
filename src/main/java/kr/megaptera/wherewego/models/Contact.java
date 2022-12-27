package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;

import javax.persistence.*;

@Embeddable
public class Contact {

    private String phone;

    private String homepage;

    public Contact() {
    }

    public Contact(String phone, String homepage) {
        this.phone = phone;
        this.homepage = homepage;
    }

    public String phone() {
        return phone;
    }

    public String homepage() {
        return homepage;
    }

    public ContactDto toDto() {
        return new ContactDto(phone, homepage);
    }

    public static Contact fake() {
        return new Contact("010-5634-1740", "https://megaptera.kr/");
    }

    @Override
    public String toString() {
        return " phone: " + phone + " homepage: " + homepage;
    }

    @Override
    public boolean equals(Object other) {
        Contact otherContact = (Contact) other;

        return phone.equals(otherContact.phone())
            && homepage.equals(otherContact.homepage());
    }
}
