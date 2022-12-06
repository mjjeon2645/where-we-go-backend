package kr.megaptera.wherewego.dtos;

public class AdminRequestDto {
    private String name;

    private String socialLoginId;

    private Long employeeIdentificationNumber;

    private String password;

    private String profileImage;

    public AdminRequestDto() {
    }

    public AdminRequestDto(String name, String socialLoginId,
                           Long employeeIdentificationNumber, String password,
                           String profileImage) {
        this.name = name;
        this.socialLoginId = socialLoginId;
        this.employeeIdentificationNumber = employeeIdentificationNumber;
        this.password = password;
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public String getSocialLoginId() {
        return socialLoginId;
    }

    public Long getEmployeeIdentificationNumber() {
        return employeeIdentificationNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
