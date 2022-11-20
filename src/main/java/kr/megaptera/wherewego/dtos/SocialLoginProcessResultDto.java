package kr.megaptera.wherewego.dtos;

public class SocialLoginProcessResultDto {
    private String accessToken;

    private String refreshToken;

    private String nickname;

    private String email;

    private String socialLoginId;

    private String authBy;


    public SocialLoginProcessResultDto() {
    }

    public SocialLoginProcessResultDto(String accessToken, String refreshToken,
                                       String nickname, String email, String socialLoginId,
                                       String authBy) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.nickname = nickname;
        this.email = email;
        this.socialLoginId = socialLoginId;
        this.authBy = authBy;
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

    public String getAuthBy() {
        return authBy;
    }
}
