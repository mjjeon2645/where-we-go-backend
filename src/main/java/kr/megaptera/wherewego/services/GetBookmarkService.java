package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
import java.util.stream.*;

@Service
@Transactional
public class GetBookmarkService {
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    public GetBookmarkService(UserRepository userRepository,
                              PlaceRepository placeRepository) {
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
    }

    public List<BookmarkedPlaceDto> bookmarks(String socialLoginId) {
        User found = userRepository.findBySocialLoginId(socialLoginId)
            .orElseThrow(UserNotFoundException::new);

        List<Bookmark> bookmarks = found.bookmarks();

        List<Place> bookmarkedPlaces = makePlacesList(bookmarks);

        return bookmarkedPlaces.stream()
            .map(Place::toBookmarkedPlaceDto)
            .collect(Collectors.toList());
    }

    public List<BookmarkedPlaceDto> bookmarks(Long id) {
        User found = userRepository.findById(id)
            .orElseThrow(UserNotFoundException::new);

        List<Bookmark> bookmarks = found.bookmarks();

        List<Place> bookmarkedPlaces = makePlacesList(bookmarks);

        return bookmarkedPlaces.stream()
            .map(Place::toBookmarkedPlaceDto)
            .collect(Collectors.toList());
    }

    public List<Place> makePlacesList(List<Bookmark> bookmarks) {
        return bookmarks.stream()
            .map(bookmark -> placeRepository.findById(bookmark.getPlaceId())
                .orElseThrow(PlaceNotFoundException::new))
            .toList();
    }
}
