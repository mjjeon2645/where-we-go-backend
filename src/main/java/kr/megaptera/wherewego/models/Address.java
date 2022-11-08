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
}
