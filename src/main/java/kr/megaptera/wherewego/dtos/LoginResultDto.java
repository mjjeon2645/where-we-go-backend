package kr.megaptera.wherewego.dtos;

public class LoginResultDto {
    private String accessToken;

    private String nickName;

    public LoginResultDto() {
    }

    public LoginResultDto(String accessToken, String nickName) {
        this.accessToken = accessToken;
        this.nickName = nickName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getNickName() {
        return nickName;
    }
}
