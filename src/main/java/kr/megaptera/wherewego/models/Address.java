package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;

import javax.persistence.*;

@Embeddable
public class Address {
  @Column(name = "place_id", insertable = false, updatable = false)
  private Long placeId;

  private String fullAddress;

  private String sido;

  private String sigungu;

  public Address() {
  }

  public Address(Long placeId, String fullAddress, String sido, String sigungu) {
    this.placeId = placeId;
    this.fullAddress = fullAddress;
    this.sido = sido;
    this.sigungu = sigungu;
  }

  public Address fake() {
    return new Address(2L, "경기도 가평군 설악면 미사리 320-1", "경기", "가평군");
  }

  public Long placeId() {
    return placeId;
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
    return new AddressDto(placeId, fullAddress, sido, sigungu);
  }

  @Override
  public boolean equals(Object other) {
    Address otherAddress = (Address) other;

    return placeId.equals(otherAddress.placeId())
        && fullAddress.equals(otherAddress.fullAddress())
        && sido.equals(otherAddress.sido())
        && sigungu.equals(otherAddress.sigungu());
  }
}
