package kr.megaptera.wherewego.dtos;

public class SocialLoginProcessResultDto {
    private String accessToken;

    private String refreshToken;

    private String nickname;

    private String email;

    private String socialLoginId;

    private String auth;

    public SocialLoginProcessResultDto() {
    }

    public SocialLoginProcessResultDto(String accessToken, String refreshToken,
                                       String nickname, String email, String socialLoginId, String auth) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.nickname = nickname;
        this.email = email;
        this.socialLoginId = socialLoginId;
        this.auth = auth;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getSocialLoginId() {
        return socialLoginId;
    }

    public String getAuth() {
        return auth;
    }
}
