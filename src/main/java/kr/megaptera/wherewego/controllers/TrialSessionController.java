package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import kr.megaptera.wherewego.utils.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("trial-session")
public class TrialSessionController {
    private final JwtUtil jwtUtil;
    private PostTrialLoginService postTrialLoginService;

    public TrialSessionController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrialLoginResultDto trialLogin(
        @RequestBody TrialLoginRequestDto trialLoginRequestDto
    ) {
        String trialId = trialLoginRequestDto.getTrialId();
        String password = trialLoginRequestDto.getPassword();

        User user = postTrialLoginService.login(trialId, password);

        String accessToken = jwtUtil.encode(identifier);

        return new TrialLoginResultDto(user.socialLoginId(), accessToken, user.nickname(), user.state());
    }

    @ExceptionHandler(LoginFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String loginFailed() {
        return "Login Failed!";
    }
}
