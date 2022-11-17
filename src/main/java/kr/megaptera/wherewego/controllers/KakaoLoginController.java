package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("oauth")
public class KakaoLoginController {

    private GetKakaoLoginService getKakaoLoginService;

    public KakaoLoginController(GetKakaoLoginService getKakaoLoginService) {
        this.getKakaoLoginService = getKakaoLoginService;
    }

    @GetMapping("kakao-token")
    public KakaoLoginDto kakaoLogin(
        @RequestParam(value = "code", required = false) String code
    ) {

        String accessToken = getKakaoLoginService.accessToken(code);
        HashMap<String, Object> userInformation = getKakaoLoginService.userInformation(accessToken);

        return null;
    }
}
