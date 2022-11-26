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

    @GetMapping()
    public ChildDtos children(
        @RequestAttribute String socialLoginId
    ) {
        List<ChildDto> children = getChildService.children(socialLoginId);

        return new ChildDtos(children);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ChildDtos create(
        @RequestAttribute String socialLoginId,
        @Validated @RequestBody ChildCreateDto childCreateDto
    ) {
        List<ChildDto> children = postChildService.create(socialLoginId, childCreateDto);

        return new ChildDtos(children);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
        @RequestAttribute String socialLoginId,
        @RequestBody ChildDeleteDto childDeleteDto
    ) {
        deleteChildService.delete(socialLoginId, childDeleteDto);
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
