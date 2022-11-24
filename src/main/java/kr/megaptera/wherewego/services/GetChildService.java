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
public class GetChildService {
    private final ChildRepository childRepository;

    public GetChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public List<ChildDto> children(Long userId) {
        List<Child> children = childRepository.findAllByUserId(userId);
        return children.stream().map(Child::toChildDto).collect(Collectors.toList());
    }
}
