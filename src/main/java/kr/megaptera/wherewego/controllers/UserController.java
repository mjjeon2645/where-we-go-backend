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
    private final UpdateUserService updateUserService;

    public UserController(GetUserService getUserService,
                          UpdateUserService updateUserService) {
        this.getUserService = getUserService;
        this.updateUserService = updateUserService;
    }

    @GetMapping()
    public UserInformationDto userInformation(
        @RequestAttribute String socialLoginId
    ) {
        User foundUser = getUserService.information(socialLoginId);

        return new UserInformationDto(foundUser.id(), foundUser.email(),
            foundUser.nickname(), foundUser.authBy());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedUserDto signUp(
        @Validated @RequestBody SetNicknameDto setNicknameDto,
        @RequestAttribute String socialLoginId
    ) {
        return updateUserService.update(socialLoginId, setNicknameDto);
    }

    @PatchMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CreatedUserDto changeNickname(
        @Validated @RequestBody SetNicknameDto setNicknameDto,
        @RequestAttribute String socialLoginId
    ) {
        return updateUserService.update(socialLoginId, setNicknameDto);
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
