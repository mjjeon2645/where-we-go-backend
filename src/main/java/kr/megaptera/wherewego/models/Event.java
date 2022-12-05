package kr.megaptera.wherewego.models;

import javax.persistence.*;

@Embeddable
public class Event {
    // 200, deletePlace

    private Long code;

    private String title;

    public Event() {
    }

    public Event(Long code, String title) {
        this.code = code;
        this.title = title;
    }

    public Long code() {
        return code;
    }

    public String title() {
        return title;
    }

    public static Event deleteUserReview() {
        return new Event(200L, "deleteUserReview");
    }

    public static Event deletePlace() {
        return new Event(201L, "deletePlace");
    }
}
