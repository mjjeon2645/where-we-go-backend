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
    private final PostLoginService postLoginService;
    private final JwtUtilForAdmin jwtUtilForAdmin;

    public AdminSessionController(PostLoginService postLoginService,
                                  JwtUtilForAdmin jwtUtilForAdmin) {
        this.postLoginService = postLoginService;
        this.jwtUtilForAdmin = jwtUtilForAdmin;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdminLoginResultDto adminLogin(
        @RequestBody LoginRequestDto loginRequestDto
        ) {
        String adminId = loginRequestDto.getIdentifier();
        String password = loginRequestDto.getPassword();

        Admin foundAdmin = postLoginService.adminLogin(adminId, password);

        String accessToken = jwtUtilForAdmin.encode(adminId);
        System.out.println(accessToken);

        return new AdminLoginResultDto(adminId, accessToken);
    }

    @ExceptionHandler(AdminLoginFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto adminLoginFailed() {
        return new AdminLoginFailedErrorDto();
    }
}
