package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
public class GetPlaceService {
    private final PlaceRepository placeRepository;

    public GetPlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<Place> places() {
        List<Place> places = placeRepository.findAll();
        return new ArrayList<>(places);
    }
}
