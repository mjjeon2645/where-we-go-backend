package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional
public class DeleteChildService {
    private final ChildRepository childRepository;
    private final UserRepository userRepository;

    public DeleteChildService(ChildRepository childRepository,
                              UserRepository userRepository) {
        this.childRepository = childRepository;
        this.userRepository = userRepository;
    }

    public void delete(String socialLoginId, ChildDeleteDto childDeleteDto) {
        User foundUser = userRepository.findBySocialLoginId(socialLoginId)
            .orElseThrow(UserNotFoundException::new);

        Long childId = childDeleteDto.getChildId();

        Child foundChild = childRepository.findById(childId)
            .orElseThrow(ChildNotFoundException::new);

        if (foundChild.userId().equals(foundUser.id())) {
            childRepository.deleteById(childId);
        }
    }
}
