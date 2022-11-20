package kr.megaptera.wherewego.dtos;

public class LoginResultDto {
    private String accessToken;

    private String nickname;

    public LoginResultDto() {
    }

    public LoginResultDto(String accessToken, String nickname) {
        this.accessToken = accessToken;
        this.nickname = nickname;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getNickname() {
        return nickname;
    }
}
