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
    @ResponseStatus(HttpStatus.CREATED)
    public ChildDtos create(
        @PathVariable Long userId,
        @Validated @RequestBody ChildCreateDto childCreateDto
    ){
        List<ChildDto> children = getChildService.create(userId, childCreateDto);

        return new ChildDtos(children);
    }

    @PatchMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
        @PathVariable Long userId,
        @RequestBody ChildDeleteDto childDeleteDto
    ){
        getChildService.delete(userId, childDeleteDto);
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
