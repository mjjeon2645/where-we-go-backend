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
    private final GetLoginService getLoginService;
    private JwtUtil jwtUtil;

    public SessionController(GetLoginService getLoginService, JwtUtil jwtUtil) {
        this.getLoginService = getLoginService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResultDto login(
        @RequestBody LoginRequestDto loginRequestDto
    ) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        User user = getLoginService.login(email, password);

        String accessToken = jwtUtil.encode(email);

        return new LoginResultDto(accessToken, user.nickName());
    }

    @ExceptionHandler(LoginFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String loginFailed() {
        return "Login Failed!";
    }
}