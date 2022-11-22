package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;

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
