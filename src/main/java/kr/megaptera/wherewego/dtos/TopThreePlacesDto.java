package kr.megaptera.wherewego.dtos;

import java.util.*;

public class TopThreePlacesDto {
    private List<TopThreePlaceDto> topThreePlaces;

    public TopThreePlacesDto() {
    }

    public TopThreePlacesDto(List<TopThreePlaceDto> topThreePlaces) {
        this.topThreePlaces = topThreePlaces;
    }

    public List<TopThreePlaceDto> getTopThreePlaces() {
        return topThreePlaces;
    }
}
