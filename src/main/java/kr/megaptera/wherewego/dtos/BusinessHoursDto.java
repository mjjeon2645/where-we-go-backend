package kr.megaptera.wherewego.dtos;

public class BusinessHoursDto {
  private Long placeId;

  private String monday;

  private String tuesday;

  private String wednesday;

  private String thursday;

  private String friday;

  private String saturday;

  private String sunday;

  public BusinessHoursDto() {
  }

  public BusinessHoursDto(Long placeId, String monday, String tuesday, String wednesday,
                          String thursday, String friday, String saturday, String sunday) {
    this.placeId = placeId;
    this.monday = monday;
    this.tuesday = tuesday;
    this.wednesday = wednesday;
    this.thursday = thursday;
    this.friday = friday;
    this.saturday = saturday;
    this.sunday = sunday;
  }

  public Long getPlaceId() {
    return placeId;
  }

  public String getMonday() {
    return monday;
  }

  public String getTuesday() {
    return tuesday;
  }

  public String getWednesday() {
    return wednesday;
  }

  public String getThursday() {
    return thursday;
  }

  public String getFriday() {
    return friday;
  }

  public String getSaturday() {
    return saturday;
  }

  public String getSunday() {
    return sunday;
  }
}
