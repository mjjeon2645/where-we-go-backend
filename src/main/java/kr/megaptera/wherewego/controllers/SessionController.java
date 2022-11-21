package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import kr.megaptera.wherewego.utils.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("session")
public class SessionController {
    private final GetLoginService getUserService;
    private final JwtUtil jwtUtil;

    public SessionController(GetLoginService getUserService, JwtUtil jwtUtil) {
        this.getUserService = getUserService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResultDto login(
        @RequestBody LoginRequestDto loginRequestDto
    ) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        User user = getUserService.login(email, password);

        String accessToken = jwtUtil.encode(email);

        return new LoginResultDto(user.id(), accessToken, user.nickname(), user.state());
    }

    @ExceptionHandler(LoginFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String loginFailed() {
        return "Login Failed!";
    }
}
