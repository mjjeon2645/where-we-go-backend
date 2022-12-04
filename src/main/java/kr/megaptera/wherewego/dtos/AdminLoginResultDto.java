package kr.megaptera.wherewego.dtos;

public class AdminLoginResultDto {
    private String adminId;

    private String accessToken;

    public AdminLoginResultDto() {
    }

    public AdminLoginResultDto(String adminId, String accessToken) {
        this.adminId = adminId;
        this.accessToken = accessToken;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
