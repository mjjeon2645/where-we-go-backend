package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.services.*;
import kr.megaptera.wherewego.utils.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("oauth")
public class KakaoLoginController {
    private final KakaoAuthUtil kakaoAuthUtil;
    private final GetLoginService getLoginService;

    public KakaoLoginController(GetLoginService getLoginService,
                                KakaoAuthUtil kakaoAuthUtil) {
        this.getLoginService = getLoginService;
        this.kakaoAuthUtil = kakaoAuthUtil;
    }

    @GetMapping("kakao-token")
    public LoginResultDto login(String code) {
        SocialLoginProcessResultDto kakaoDto = kakaoAuthUtil.process(code);

        return getLoginService.socialLogin(kakaoDto);
    }
}
