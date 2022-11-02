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


  public String getFullAddress() {
    return fullAddress;
  }

  public String getSido() {
    return sido;
  }

  public String getSigungu() {
    return sigungu;
  }

  public Long getPlaceId() {
    return placeId;
  }
}
