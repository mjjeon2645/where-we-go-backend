package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;

import javax.persistence.*;

@Embeddable
public class BusinessHours {
  @Column(name = "place_id", insertable = false, updatable = false)
  private Long placeId;

  @Column(name = "monday")
  private String monday;

  @Column(name = "tuesday")
  private String tuesday;

  @Column(name = "wednesday")
  private String wednesday;

  @Column(name = "thursday")
  private String thursday;

  @Column(name = "friday")
  private String friday;

  @Column(name = "saturday")
  private String saturday;

  @Column(name = "sunday")
  private String sunday;

  public BusinessHours() {
  }

  public BusinessHours(Long placeId, String monday, String tuesday, String wednesday,
                       String thursday, String friday, String saturday,
                       String sunday) {
    this.placeId = placeId;
    this.monday = monday;
    this.tuesday = tuesday;
    this.wednesday = wednesday;
    this.thursday = thursday;
    this.friday = friday;
    this.saturday = saturday;
    this.sunday = sunday;
  }

  public Long placeId() {
    return placeId;
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

  @Override
  public boolean equals(Object other) {
    BusinessHours otherBusinessHours = (BusinessHours) other;

    return monday.equals(otherBusinessHours.monday())
        && tuesday.equals(otherBusinessHours.tuesday())
        && wednesday.equals(otherBusinessHours.wednesday())
        && thursday.equals(otherBusinessHours.thursday())
        && friday.equals(otherBusinessHours.friday())
        && saturday.equals(otherBusinessHours.saturday())
        && sunday.equals(otherBusinessHours.sunday())
        && placeId.equals(otherBusinessHours.placeId());
  }

  public BusinessHoursDto toDto() {
    return new BusinessHoursDto(placeId, monday, tuesday, wednesday, thursday,
        friday, saturday, sunday);
  }
}
