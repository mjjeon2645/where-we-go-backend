package kr.megaptera.wherewego.errorDtos;

public class AdminPasswordErrorDto extends ErrorDto {
    public AdminPasswordErrorDto() {
        super(7006, "비밀번호가 맞지 않습니다. 다시 확인해주세요");
    }
}
