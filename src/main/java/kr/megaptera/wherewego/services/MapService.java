package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
public class MapService {
  private PlaceRepository placeRepository;

  public MapService(PlaceRepository placeRepository) {
    this.placeRepository = placeRepository;
  }

  public List<Place> places() {
    return placeRepository.findAll();
  }
}
