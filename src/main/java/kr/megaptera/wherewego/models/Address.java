package kr.megaptera.wherewego.models;

import javax.persistence.*;

public class Address {

  @Id
  @GeneratedValue
  private Long id;

  private String fullAddress;

  private String sido;

  private String sigungu;

  private Long placeId;

  public Address(Long id, String fullAddress, String sido, String sigungu, Long placeId) {
    this.id = id;
    this.fullAddress = fullAddress;
    this.sido = sido;
    this.sigungu = sigungu;
    this.placeId = placeId;
  }

  public Long id() {
    return id;
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
}
