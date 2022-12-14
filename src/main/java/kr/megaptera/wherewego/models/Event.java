package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;

import javax.persistence.*;

@Embeddable
public class Event {

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

    public static Event deleteUser() {
        return new Event(202L, "deleteUser");
    }

    public EventDto toDto() {
        return new EventDto(code, title);
    }
}
