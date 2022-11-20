package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
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

        return new UserInformationDto();
    }
}
