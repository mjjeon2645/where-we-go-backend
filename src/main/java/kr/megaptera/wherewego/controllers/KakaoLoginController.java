package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.services.*;
import kr.megaptera.wherewego.utils.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("oauth")
public class KakaoLoginController {
    private final KakaoLoginUtil kakaoLoginUtil;
    private GetUserService getUserService;

    public KakaoLoginController(GetUserService getUserService, KakaoLoginUtil kakaoLoginUtil) {
        this.getUserService = getUserService;
        this.kakaoLoginUtil = kakaoLoginUtil;
    }

    @GetMapping("kakao-token")
    public LoginResultDto login(String code) {
        SocialLoginProcessResultDto kakaoDto = kakaoLoginUtil.process(code);

        LoginResultDto loginResultDto = getUserService.socialLogin(kakaoDto);

       return loginResultDto;
    }
}
