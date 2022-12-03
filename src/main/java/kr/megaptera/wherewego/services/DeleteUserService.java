package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
public class DeleteUserService {
    private final UserRepository userRepository;
    private final ChildRepository childRepository;
    private final UserReviewRepository userReviewRepository;

    public DeleteUserService(UserRepository userRepository,
                             ChildRepository childRepository,
                             UserReviewRepository userReviewRepository) {
        this.userRepository = userRepository;
        this.childRepository = childRepository;
        this.userReviewRepository = userReviewRepository;
    }

    public void deleteTrialUser(String socialLoginId) {
        User foundToDelete = userRepository.findBySocialLoginId(socialLoginId)
            .orElseThrow(UserNotFoundException::new);

        deleteProcess(foundToDelete);
    }

    public void deleteUser(Long id) {
        User foundToDelete = userRepository.findById(id)
            .orElseThrow(UserNotFoundException::new);

        deleteProcess(foundToDelete);
    }

    public void deleteProcess(User foundToDelete) {
        List<Bookmark> bookmarks = foundToDelete.bookmarks();

        if (bookmarks != null && bookmarks.size() > 0) {
            bookmarks.subList(0, bookmarks.size()).clear();
        }

        Long userId = foundToDelete.id();

        childRepository.deleteAllByUserId(userId);

        userReviewRepository.deleteAllByUserId(userId);

        userRepository.deleteById(userId);
    }
}
