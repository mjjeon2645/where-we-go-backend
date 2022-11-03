package kr.megaptera.wherewego.models;

import javax.persistence.*;

@Embeddable
public class Address {

  @Column(name = "full_address")
  private String fullAddress;

  @Column(name = "sido")
  private String sido;

  @Column(name = "sigungu")
  private String sigungu;

  @Column(name = "place_id", insertable = false, updatable = false)
  private Long placeId;

  public Address() {
  }

  public Address(String fullAddress, String sido, String sigungu, Long placeId) {
    this.fullAddress = fullAddress;
    this.sido = sido;
    this.sigungu = sigungu;
    this.placeId = placeId;
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

  public Long placeId() {
    return placeId;
  }

  @Override
  public boolean equals(Object other) {
    Address otherAddress = (Address) other;

    return fullAddress.equals(otherAddress.fullAddress())
        && sido.equals(otherAddress.sido())
        && sigungu.equals(otherAddress.sigungu())
        && placeId.equals(otherAddress.placeId());
  }
}
