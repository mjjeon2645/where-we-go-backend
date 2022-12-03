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
public class GetChildService {
    private final ChildRepository childRepository;
    private final UserRepository userRepository;

    public GetChildService(ChildRepository childRepository,
                           UserRepository userRepository) {
        this.childRepository = childRepository;
        this.userRepository = userRepository;
    }

    public List<ChildDto> children(String socialLoginId) {
        User found = userRepository.findBySocialLoginId(socialLoginId)
            .orElseThrow(UserNotFoundException::new);

        List<Child> children = childRepository.findAllByUserId(found.id());

        return children.stream().map(Child::toChildDto).collect(Collectors.toList());
    }

    public List<ChildDto> children(Long id) {
        User found = userRepository.findById(id)
            .orElseThrow(UserNotFoundException::new);

        List<Child> children = childRepository.findAllByUserId(found.id());

        return children.stream().map(Child::toChildDto).collect(Collectors.toList());
    }
}
