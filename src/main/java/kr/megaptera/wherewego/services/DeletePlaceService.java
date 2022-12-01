package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
import java.util.stream.*;

@Service
@Transactional
public class DeletePlaceService {
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final UserReviewRepository userReviewRepository;

    public DeletePlaceService(UserRepository userRepository,
                              PlaceRepository placeRepository,
                              UserReviewRepository userReviewRepository) {
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
        this.userReviewRepository = userReviewRepository;
    }

    public void delete(Long id) {
        // 유저의 북마크가 해당 아이디면 지워줘야 함
        List<User> users = userRepository.findAll();

        users.stream().map(User::bookmarks)
            .map(bookmarks ->
                bookmarks.stream().map(bookmark
                    -> bookmark.getPlaceId().equals(id)
                    ? bookmarks.remove(bookmark)
                    : bookmark))
            .toList();


//            .stream().map(bookmark -> {
//            if (bookmark.getPlaceId().equals(id)) {
//                user.bookmarks().remove(bookmark);
//            }
//            return bookmark;
//        }));

        // 유저 리뷰의 placeId가 해당 아이디면 지워줘야 함
        userReviewRepository.deleteAllByPlaceId(id);

        placeRepository.deleteById(id);
    }
}
