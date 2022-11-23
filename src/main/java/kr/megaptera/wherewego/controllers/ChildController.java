package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("children")
public class ChildController {
    private final GetChildService getChildService;

    public ChildController(GetChildService getChildService) {
        this.getChildService = getChildService;
    }

    @GetMapping("{userId}")
    public ChildDtos children(
        @PathVariable Long userId
    ) {
        List<ChildDto> children = getChildService.children(userId);

        return new ChildDtos(children);
    }

    @PostMapping("{userId}")
    public ChildDtos children(
        @PathVariable Long userId,
        @RequestBody ChildRequestDto childRequestDto
    ){
        List<ChildDto> children = getChildService.create(userId, childRequestDto);

        return new ChildDtos(children);
    }

}
