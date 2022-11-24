package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional
public class DeleteChildService {
    private final ChildRepository childRepository;

    public DeleteChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public void delete(ChildDeleteDto childDeleteDto) {
        Long childId = childDeleteDto.getChildId();

        childRepository.deleteById(childId);
    }
}
