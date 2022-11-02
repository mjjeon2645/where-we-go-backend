package kr.megaptera.wherewego.models;

import javax.persistence.*;

@Entity
public class BusinessHours {

  @Id
  @GeneratedValue
  private Long id;

  private String monday;

  private String tuesday;

  private String wednesday;

  private String thursday;

  private String friday;

  private String saturday;

  private String sunday;

  private Long placeId;

  public BusinessHours(Long id, String monday, String tuesday,
                       String wednesday, String thursday, String friday,
                       String saturday, String sunday, Long placeId) {
    this.id = id;
    this.monday = monday;
    this.tuesday = tuesday;
    this.wednesday = wednesday;
    this.thursday = thursday;
    this.friday = friday;
    this.saturday = saturday;
    this.sunday = sunday;
    this.placeId = placeId;
  }

  public Long id() {
    return id;
  }

  public String monday() {
    return monday;
  }

  public String tuesday() {
    return tuesday;
  }

  public String wednesday() {
    return wednesday;
  }

  public String thursday() {
    return thursday;
  }

  public String friday() {
    return friday;
  }

  public String saturday() {
    return saturday;
  }

  public String sunday() {
    return sunday;
  }

  public Long placeId() {
    return placeId;
  }
}
