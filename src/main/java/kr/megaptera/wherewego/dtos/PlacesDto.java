package kr.megaptera.wherewego.dtos;

import java.util.*;

public class PlacesDto {
    private List<PlaceDto> places;

    public PlacesDto() {
    }

    public PlacesDto(List<PlaceDto> places) {
        this.places = places;
    }

    public List<PlaceDto> getPlaces() {
        return places;
    }
}
