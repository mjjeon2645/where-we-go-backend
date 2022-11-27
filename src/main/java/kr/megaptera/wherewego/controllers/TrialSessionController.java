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
    private final PostTrialUserService postTrialUserService;
    private final DeleteTrialUserService deleteTrialUserService;
    private final JwtUtil jwtUtil;

    public TrialSessionController(PostTrialUserService postTrialUserService,
                                  DeleteTrialUserService deleteTrialUserService, JwtUtil jwtUtil) {
        this.postTrialUserService = postTrialUserService;
        this.deleteTrialUserService = deleteTrialUserService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrialLoginResultDto trial(
        @RequestBody TrialLoginRequestDto trialLoginRequestDto
    ) {
        User trialUser = postTrialUserService.create(trialLoginRequestDto);

        String accessToken = jwtUtil.encode(trialLoginRequestDto.getPassword());

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
