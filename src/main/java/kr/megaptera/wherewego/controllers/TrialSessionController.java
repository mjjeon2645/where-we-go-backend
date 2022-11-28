package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.errorDtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import kr.megaptera.wherewego.utils.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("trial-session")
public class TrialSessionController {
    private final GetTrialUserService getTrialUserService;
    private final DeleteTrialUserService deleteTrialUserService;
    private final JwtUtil jwtUtil;

    public TrialSessionController(GetTrialUserService getTrialUserService,
                                  DeleteTrialUserService deleteTrialUserService, JwtUtil jwtUtil) {
        this.getTrialUserService = getTrialUserService;
        this.deleteTrialUserService = deleteTrialUserService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public TrialLoginResultDto trialLoginResultDto() {
        User trialUser = getTrialUserService.trialUser();

        String accessToken = jwtUtil.encode(trialUser.socialLoginId());

        return new TrialLoginResultDto(accessToken, trialUser.nickname(), trialUser.state());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrialUserInformation(
        @RequestAttribute String socialLoginId
    ) {
        deleteTrialUserService.delete(socialLoginId);
    }

    @ExceptionHandler(LoginFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String loginFailed() {
        return "Login Failed!";
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto userNotFoundError() {
        return new UserNotFoundErrorDto();
    }
}
