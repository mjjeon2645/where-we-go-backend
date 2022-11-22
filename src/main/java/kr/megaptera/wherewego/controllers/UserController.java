package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.errorDtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.springframework.http.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    private final GetUserService getUserService;

    public UserController(GetUserService getUserService) {
        this.getUserService = getUserService;
    }

    @GetMapping("{userId}")
    public UserInformationDto userInformation(
        @PathVariable() Long userId
    ) {
        User foundUser = getUserService.information(userId);

        return new UserInformationDto(foundUser.id(), foundUser.email(),
            foundUser.nickname(), foundUser.authBy());
    }

    @PostMapping("{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedUserDto signUp(
        @PathVariable() Long userId,
        @Validated @RequestBody SetNicknameDto setNicknameDto
    ) {
        return getUserService.update(userId, setNicknameDto);
    }

    @PatchMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CreatedUserDto changeNickname(
        @PathVariable() Long userId,
        @Validated @RequestBody SetNicknameDto setNicknameDto
    ) {
        return getUserService.update(userId, setNicknameDto);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto userNotFound() {
        return new UserNotFoundErrorDto();
    }

    @ExceptionHandler(UnchangedNicknameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto unchangedNickname() {
        return new UnchangedNicknameErrorDto();
    }

    @ExceptionHandler(NicknameDuplicatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto nicknameDuplicated() {
        return new NicknameDuplicatedErrorDto();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto nicknameValidationError(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        for (ObjectError error : result.getAllErrors()) {
            return new NicknameValidationErrorDto(1004, error.getDefaultMessage());
        }
        return new NicknameValidationErrorDto(1005, "Bad Request!");
    }
}
