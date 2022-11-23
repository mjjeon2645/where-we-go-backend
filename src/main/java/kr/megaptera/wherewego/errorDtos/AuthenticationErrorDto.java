package kr.megaptera.wherewego.errorDtos;

public class AuthenticationErrorDto extends ErrorDto {
    public AuthenticationErrorDto() {
        super(5001, "접근 권한이 없습니다.");
    }
}
