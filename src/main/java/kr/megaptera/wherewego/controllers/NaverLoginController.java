package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.infrastructure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("oauth")
public class NaverLoginController {
    private final NaverLoginService naverLoginService;

    public NaverLoginController(NaverLoginService naverLoginService) {
        this.naverLoginService = naverLoginService;
    }

    @GetMapping("naver-token")
    public LoginResultDto login(String code) {
        LoginResultDto loginResultDto = naverLoginService.naverLogin(code);

       return loginResultDto;
    }
}
