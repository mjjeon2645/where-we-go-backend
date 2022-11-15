package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
public class GetTopThreePlacesService {
    private final PlaceRepository placeRepository;
    private final UserReviewRepository userReviewRepository;

    public GetTopThreePlacesService(PlaceRepository placeRepository,
                                    UserReviewRepository userReviewRepository) {
        this.placeRepository = placeRepository;
        this.userReviewRepository = userReviewRepository;
    }

    public List<TopThreePlaceDto> topThreePlaces() {

        List<Long> placeIds = placeRepository.findAll().stream().map(Place::id).toList();

        Map<Long, Double> averageRatesSet = new HashMap<>();

        makeMapWithPlaceIdAndAverageRate(placeIds, averageRatesSet);

        Set<Map.Entry<Long, Double>> entries = averageRatesSet.entrySet();

        List<Map.Entry<Long, Double>> results = entries.stream()
            .sorted(Map.Entry.comparingByValue((a, b) -> (int) ((b - a) * 100)))
            .toList();

        List<TopThreePlaceDto> topThreePlaces = new ArrayList<>();

        makeTopThreePlacesWithResults(topThreePlaces, results);

//        Long firstPlaceId = results.get(0).getKey();
//        Long secondPlaceId = results.get(1).getKey();
//        Long thirdPlaceId = results.get(2).getKey();
//
//        Double firstPlaceAverageRate = results.get(0).getValue();
//        Double secondPlaceAverageRate = results.get(1).getValue();
//        Double thirdPlaceAverageRate = results.get(2).getValue();
//
//        String firstPlaceName = placeRepository.getReferenceById(firstPlaceId).name();
//        String secondPlaceName = placeRepository.getReferenceById(secondPlaceId).name();
//        String thirdPlaceName = placeRepository.getReferenceById(thirdPlaceId).name();
//
//        Address firstPlaceAddress = placeRepository.getReferenceById(firstPlaceId).address();
//        Address secondPlaceAddress = placeRepository.getReferenceById(secondPlaceId).address();
//        Address thirdPlaceAddress = placeRepository.getReferenceById(thirdPlaceId).address();
//
//
//        topThreePlaces.add(new TopThreePlaceDto(firstPlaceId, firstPlaceName, firstPlaceAddress.toDto(), firstPlaceAverageRate));
//        topThreePlaces.add(new TopThreePlaceDto(secondPlaceId, secondPlaceName, secondPlaceAddress.toDto(), secondPlaceAverageRate));
//        topThreePlaces.add(new TopThreePlaceDto(thirdPlaceId, thirdPlaceName, thirdPlaceAddress.toDto(), thirdPlaceAverageRate));

        return topThreePlaces;
    }

    public void makeMapWithPlaceIdAndAverageRate(List<Long> placeIds, Map<Long, Double> averageRatesSet) {
        for (Long placeId : placeIds) {
            List<UserReview> filteredUserReviews = userReviewRepository
                .findAllByPlaceId(placeId).stream().toList();

            List<Long> rates = filteredUserReviews.stream().map(UserReview::rate).toList();

            Double averageRate = rates.stream().mapToDouble(num -> num).summaryStatistics().getAverage();

            averageRatesSet.put(placeId, averageRate);
        }
    }

    public void makeTopThreePlacesWithResults(List<TopThreePlaceDto> topThreePlaces, List<Map.Entry<Long, Double>> results) {
        for (int i = 0; i < 3; i += 1) {
          Long placeId = results.get(i).getKey();
          String name = placeRepository.getReferenceById(placeId).name();
          Address address = placeRepository.getReferenceById(placeId).address();
          String averageRate = process(results.get(i).getValue());

          topThreePlaces.add(new TopThreePlaceDto(placeId, name, address.toDto(), averageRate));
        }
    }

    public String process(Double value) {
        String averageToString = String.format("%.2f", value);

        if (averageToString.substring(2).equals("00")) {
            return averageToString.substring(0, 1);
        }

        if (averageToString.substring(3).equals("0")) {
            return averageToString.substring(0, 3);
        }

        return averageToString;
    }
}
