package kr.megaptera.wherewego.dtos;

public class AdminInformationDto {
    private String socialLoginId;

    private String name;

    private Long employeeIdentificationNumber;

    private String profileImage;

    public AdminInformationDto() {
    }

    public AdminInformationDto(String socialLoginId, String name,
                               Long employeeIdentificationNumber, String profileImage) {
        this.socialLoginId = socialLoginId;
        this.name = name;
        this.employeeIdentificationNumber = employeeIdentificationNumber;
        this.profileImage = profileImage;
    }

    public String getSocialLoginId() {
        return socialLoginId;
    }

    public String getName() {
        return name;
    }

    public Long getEmployeeIdentificationNumber() {
        return employeeIdentificationNumber;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
