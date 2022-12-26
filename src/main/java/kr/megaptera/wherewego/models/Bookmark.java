package kr.megaptera.wherewego.models;

import javax.persistence.*;

@Embeddable
public class Bookmark {
    private Long placeId;

    public Bookmark() {
    }

    public Bookmark(Long placeId) {
        this.placeId = placeId;
    }

    public Long getPlaceId() {
        return placeId;
    }
}
