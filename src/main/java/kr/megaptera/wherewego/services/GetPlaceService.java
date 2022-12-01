package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.data.domain.*;
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
        List<Place> places = placeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return new ArrayList<>(places);
    }

    public Place selectedPlace(Long id) {
        return placeRepository.findById(id)
            .orElseThrow(PlaceNotFoundException::new);
    }
}
