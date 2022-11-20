package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.services.*;
import kr.megaptera.wherewego.utils.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("oauth")
public class NaverLoginController {
    private final NaverLoginUtil naverLoginUtil;
    private GetUserService getUserService;

    public NaverLoginController(GetUserService getUserService, NaverLoginUtil naverLoginUtil) {
        this.getUserService = getUserService;
        this.naverLoginUtil = naverLoginUtil;
    }

    @GetMapping("naver-token")
    public LoginResultDto login(String code) {
        SocialLoginProcessResultDto naverDto = naverLoginUtil.process(code);

        LoginResultDto loginResultDto = getUserService.socialLogin(naverDto);
       return loginResultDto;
    }
}
