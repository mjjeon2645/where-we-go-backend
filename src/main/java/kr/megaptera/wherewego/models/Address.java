package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;

import javax.persistence.*;

@Embeddable
public class Address {

    private String fullAddress;

    private String sido;

    private String sigungu;

    public Address() {
    }

    public Address(String fullAddress, String sido, String sigungu) {
        this.fullAddress = fullAddress;
        this.sido = sido;
        this.sigungu = sigungu;
    }

    public static Address fake1() {
        return new Address("경기도 과천시 블라블라", "경기", "과천시");
    }

    public static Address fake2() {
        return new Address("서울시 성동구", "서울", "성동구");
    }

    public String fullAddress() {
        return fullAddress;
    }

    public String sido() {
        return sido;
    }

    public String sigungu() {
        return sigungu;
    }

    public AddressDto toDto() {
        return new AddressDto(fullAddress, sido, sigungu);
    }

    @Override
    public boolean equals(Object other) {
        Address otherAddress = (Address) other;

        return fullAddress.equals(otherAddress.fullAddress())
            && sido.equals(otherAddress.sido())
            && sigungu.equals(otherAddress.sigungu());
    }

    @Override
    public String toString() {
        return " sido: " + sido + " sigungu: " + sigungu;
    }
}
