package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    private GetUserService getUserService;

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

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userNotFound() {
        return "사용자 정보를 찾을 수 없습니다";
    }
}
