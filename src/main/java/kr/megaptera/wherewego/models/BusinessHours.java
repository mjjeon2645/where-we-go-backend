package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;

import javax.persistence.*;

@Embeddable
public class BusinessHours {
//    @Column(name = "place_id", insertable = false, updatable = false)
//    private Long placeId;

    private String monday;

    private String tuesday;

    private String wednesday;

    private String thursday;

    private String friday;

    private String saturday;

    private String sunday;

    public BusinessHours() {
    }

//    public BusinessHours(Long placeId, String monday, String tuesday, String wednesday,
//                         String thursday, String friday, String saturday,
//                         String sunday) {
//        this.placeId = placeId;
//        this.monday = monday;
//        this.tuesday = tuesday;
//        this.wednesday = wednesday;
//        this.thursday = thursday;
//        this.friday = friday;
//        this.saturday = saturday;
//        this.sunday = sunday;
//    }

    public BusinessHours(String monday, String tuesday, String wednesday,
                         String thursday, String friday, String saturday,
                         String sunday) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

//    public BusinessHoursDto toDto() {
//        return new BusinessHoursDto(placeId, monday, tuesday, wednesday, thursday,
//            friday, saturday, sunday);
//    }

    public BusinessHoursDto toDto() {
        return new BusinessHoursDto(monday, tuesday, wednesday, thursday,
            friday, saturday, sunday);
    }

    public static BusinessHours fake() {
        return new BusinessHours(
//            1L,
            "월요일: 10:01~18:00",
            "화요일: 10:02~18:00",
            "수요일: 10:03~18:00",
            "목요일: 10:04~18:00",
            "금요일: 10:05~18:00",
            "토요일: 10:06~18:00",
            "일요일: 10:07~18:00"
        );
    }

//    public Long placeId() {
//        return placeId;
//    }

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

//    @Override
//    public String toString() {
//        return "placeId: " + placeId + " monday: " + monday + " tuesday: " + tuesday +
//            " wednesday: " + wednesday + " thursday: " + thursday + " friday: " + friday +
//            " saturday: " + saturday + " sunday: " + sunday;
//    }

    @Override
    public String toString() {
        return " monday: " + monday + " tuesday: " + tuesday +
            " wednesday: " + wednesday + " thursday: " + thursday + " friday: " + friday +
            " saturday: " + saturday + " sunday: " + sunday;
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
            && sunday.equals(otherBusinessHours.sunday());
    }

//    @Override
//    public boolean equals(Object other) {
//        BusinessHours otherBusinessHours = (BusinessHours) other;
//
//        return monday.equals(otherBusinessHours.monday())
//            && tuesday.equals(otherBusinessHours.tuesday())
//            && wednesday.equals(otherBusinessHours.wednesday())
//            && thursday.equals(otherBusinessHours.thursday())
//            && friday.equals(otherBusinessHours.friday())
//            && saturday.equals(otherBusinessHours.saturday())
//            && sunday.equals(otherBusinessHours.sunday())
//            && placeId.equals(otherBusinessHours.placeId());
//    }
}
