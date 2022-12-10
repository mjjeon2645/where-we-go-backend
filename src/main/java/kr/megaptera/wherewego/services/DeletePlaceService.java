package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

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
        // 유저의 북마크의 장소 id가 해당 아이디면 지워줘야 함
        List<User> users = userRepository.findAll();

        for (User user : users) {
            int size = user.bookmarks().size();

            if (size == 0) {
                continue;
            }

            for (int j = size - 1; j >= 0; j -= 1) {
                Bookmark found = user.bookmarks().get(j);

                if (Objects.equals(found.getPlaceId(), id)) {
                    user.bookmarks().remove(found);
                }
            }
        }

        // 유저 리뷰의 placeId가 해당 아이디면 지워줘야 함
        userReviewRepository.deleteAllByPlaceId(id);

        placeRepository.deleteById(id);
    }
}
