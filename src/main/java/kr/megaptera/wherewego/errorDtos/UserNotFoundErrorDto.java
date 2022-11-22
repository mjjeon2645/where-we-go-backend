package kr.megaptera.wherewego.errorDtos;

public class UserNotFoundErrorDto extends ErrorDto{
    public UserNotFoundErrorDto() {
        super(1000, "사용자 정보를 찾을 수 없습니다");
    }
}
