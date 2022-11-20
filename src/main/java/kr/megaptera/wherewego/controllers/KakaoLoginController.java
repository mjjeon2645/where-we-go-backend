package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.utils.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("oauth")
public class KakaoLoginController {
    private final KakaoLoginUtil kakaoLoginUtil;

    public KakaoLoginController(KakaoLoginUtil kakaoLoginUtil) {
        this.kakaoLoginUtil = kakaoLoginUtil;
    }

    @GetMapping("kakao-token")
    public LoginResultDto login(String code) {
        LoginResultDto loginResultDto = kakaoLoginUtil.proceess(code);

       return loginResultDto;
    }
}
