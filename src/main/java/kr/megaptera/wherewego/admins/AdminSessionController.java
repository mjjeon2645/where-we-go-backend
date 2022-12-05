package kr.megaptera.wherewego.admins;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.errorDtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import kr.megaptera.wherewego.utils.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin-session")
public class AdminSessionController {
    private final GetAdminService getAdminService;
    private final PostLoginService postLoginService;
    private final JwtUtil jwtUtil;

    public AdminSessionController(GetAdminService getAdminService,
                                  PostLoginService postLoginService, JwtUtil jwtUtil) {
        this.getAdminService = getAdminService;
        this.postLoginService = postLoginService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public AdminLoginResultDto adminInformation(
        @RequestAttribute String socialLoginId
    ) {
        Admin foundAdmin = getAdminService.admin(socialLoginId);

        return new AdminLoginResultDto(foundAdmin.socialLoginId(), null,
            foundAdmin.employeeIdentificationNumber());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdminLoginResultDto adminLogin(
        @RequestBody LoginRequestDto loginRequestDto
        ) {
        String socialLoginId = loginRequestDto.getSocialLoginId();
        String password = loginRequestDto.getPassword();

        Admin foundAdmin = postLoginService.adminLogin(socialLoginId, password);

        String accessToken = jwtUtil.encode(socialLoginId);

        return new AdminLoginResultDto(socialLoginId, accessToken,
            foundAdmin.employeeIdentificationNumber());
    }

    @ExceptionHandler(AdminLoginFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto adminLoginFailed() {
        return new AdminLoginFailedErrorDto();
    }

    @ExceptionHandler(AuthenticationError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto authenticationError() {
        return new AuthenticationErrorDto();
    }
}
