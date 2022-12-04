package kr.megaptera.wherewego.dtos;

public class AdminLoginResultDto {
    private String socialLoginId;

    private String accessToken;

    public AdminLoginResultDto() {
    }

    public AdminLoginResultDto(String socialLoginId, String accessToken) {
        this.socialLoginId = socialLoginId;
        this.accessToken = accessToken;
    }

    public String getSocialLoginId() {
        return socialLoginId;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
