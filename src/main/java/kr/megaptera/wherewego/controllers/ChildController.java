package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.errorDtos.*;
import kr.megaptera.wherewego.services.*;
import org.springframework.http.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("children")
public class ChildController {
    private final GetChildService getChildService;
    private final PostChildService postChildService;
    private final DeleteChildService deleteChildService;

    public ChildController(GetChildService getChildService,
                           PostChildService postChildService,
                           DeleteChildService deleteChildService) {
        this.getChildService = getChildService;
        this.postChildService = postChildService;
        this.deleteChildService = deleteChildService;
    }

    @GetMapping("{userId}")
    public ChildDtos children(
        @PathVariable Long userId
    ) {
        List<ChildDto> children = getChildService.children(userId);

        return new ChildDtos(children);
    }

    @PostMapping("{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ChildDtos create(
        @PathVariable Long userId,
        @Validated @RequestBody ChildCreateDto childCreateDto
    ) {
        List<ChildDto> children = postChildService.create(userId, childCreateDto);

        return new ChildDtos(children);
    }

    @DeleteMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
        @PathVariable Long userId,
        @RequestBody ChildDeleteDto childDeleteDto
    ) {
        deleteChildService.delete(childDeleteDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto childInformationMissing(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        for (ObjectError error : result.getAllErrors()) {
            return new ChildInformationMissingDto(4001, error.getDefaultMessage());
        }
        return new ChildInformationMissingDto(4002, "Bad Request!");
    }
}
