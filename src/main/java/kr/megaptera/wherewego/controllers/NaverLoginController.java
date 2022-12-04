package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.services.*;
import kr.megaptera.wherewego.utils.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("oauth")
public class NaverLoginController {
    private final NaverAuthUtil naverAuthUtil;
    private final PostLoginService postLoginService;

    public NaverLoginController(PostLoginService getLoginService,
                                NaverAuthUtil naverAuthUtil) {
        this.postLoginService = getLoginService;
        this.naverAuthUtil = naverAuthUtil;
    }

    @GetMapping("naver-token")
    public LoginResultDto login(String code) {
        SocialLoginProcessResultDto naverDto = naverAuthUtil.process(code);

        return postLoginService.socialLogin(naverDto);
    }
}
