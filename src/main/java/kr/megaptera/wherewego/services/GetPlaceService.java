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
    private final AdminRepository adminRepository;

    public GetPlaceService(PlaceRepository placeRepository,
                           AdminRepository adminRepository) {
        this.placeRepository = placeRepository;
        this.adminRepository = adminRepository;
    }

    public List<Place> places(String socialLoginId) {
        Admin found = adminRepository.findBySocialLoginId(socialLoginId)
            .orElseThrow(AuthenticationError::new);

        List<Place> places = placeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        return new ArrayList<>(places);
    }

    public Place selectedPlace(Long id, String socialLoginId) {
        Admin found = adminRepository.findBySocialLoginId(socialLoginId)
            .orElseThrow(AuthenticationError::new);

        return placeRepository.findById(id)
            .orElseThrow(PlaceNotFoundException::new);
    }
}
