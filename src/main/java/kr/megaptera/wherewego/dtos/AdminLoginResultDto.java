package kr.megaptera.wherewego.dtos;

public class AdminLoginResultDto {
    private String socialLoginId;

    private String accessToken;

    private Long employeeIdentificationNumber;

    public AdminLoginResultDto() {
    }

    public AdminLoginResultDto(String socialLoginId, String accessToken,
                               Long employeeIdentificationNumber) {
        this.socialLoginId = socialLoginId;
        this.accessToken = accessToken;
        this.employeeIdentificationNumber = employeeIdentificationNumber;
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
}
