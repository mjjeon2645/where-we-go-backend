package kr.megaptera.wherewego.dtos;

public class AdminLoginResultDto {
    private String socialLoginId;

    private String accessToken;

    private Long employeeIdentificationNumber;

    private String profileImage;

    public AdminLoginResultDto() {
    }

    public AdminLoginResultDto(String socialLoginId, String accessToken,
                               Long employeeIdentificationNumber, String profileImage) {
        this.socialLoginId = socialLoginId;
        this.accessToken = accessToken;
        this.employeeIdentificationNumber = employeeIdentificationNumber;
        this.profileImage = profileImage;
    }

    public String getSocialLoginId() {
        return socialLoginId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Long getEmployeeIdentificationNumber() {
        return employeeIdentificationNumber;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
