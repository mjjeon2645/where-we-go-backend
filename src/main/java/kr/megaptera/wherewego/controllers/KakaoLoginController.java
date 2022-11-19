package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.infrastructure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("oauth")
public class KakaoLoginController {
    private final KakaoLoginService kakaoLoginService;

    public KakaoLoginController(KakaoLoginService kakaoLoginService) {
        this.kakaoLoginService = kakaoLoginService;
    }

    @GetMapping("kakao-token")
    public LoginResultDto login(String code) {
        LoginResultDto loginResultDto = kakaoLoginService.kakaoLogin(code);

       return loginResultDto;
    }
}
