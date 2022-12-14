package kr.megaptera.wherewego.admins;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.errorDtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin-signup")
public class AdminSignUpController {
    private final AdminSignUpService adminSignUpService;

    public AdminSignUpController(AdminSignUpService adminSignUpService) {
        this.adminSignUpService = adminSignUpService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAdminDto adminSignUp(
        @RequestBody AdminRequestDto adminRequestDto
    ) {
        Admin createdAdmin = adminSignUpService.signUp(adminRequestDto);

        return createdAdmin.toDto();
    }

    @ExceptionHandler(ExistAdminMemberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto existAdminMemberError() {
        return new ExistAdminMemberErrorDto();
    }

    @ExceptionHandler(DuplicateIdentifierException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto duplicatedIdError() {
        return new DuplicateIdentifierErrorDto();
    }
}
