package kr.megaptera.wherewego.dtos;

public class LoginRequestDto {
    private String socialLoginId;

    private String password;

    public LoginRequestDto() {
    }

    public LoginRequestDto(String socialLoginId, String password) {
        this.socialLoginId = socialLoginId;
        this.password = password;
    }

    public String getSocialLoginId() {
        return socialLoginId;
    }

    public String getPassword() {
        return password;
    }
}
