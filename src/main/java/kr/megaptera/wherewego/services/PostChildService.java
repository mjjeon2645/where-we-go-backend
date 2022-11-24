package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
import java.util.stream.*;

@Service
@Transactional
public class PostChildService {
    private final ChildRepository childRepository;

    public PostChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public List<ChildDto> create(Long userId, ChildCreateDto childCreateDto) {
        String birthday = childCreateDto.getBirthday();
        String gender = childCreateDto.getGender();

        if (gender.equals("왕자님")) {
            gender = Child.BOY;
        }

        if (gender.equals("공주님")) {
            gender = Child.GIRL;
        }

        if (gender.equals("아직 몰라요")) {
            gender = Child.UNDEFINED;
        }

        Child createdChild = new Child(userId, gender, birthday);

        childRepository.save(createdChild);

        return childRepository.findAllByUserId(userId)
            .stream()
            .map(Child::toChildDto)
            .collect(Collectors.toList());
    }
}
