package kr.megaptera.wherewego.dtos;

public class AdminRequestDto {
    private String name;

    private String socialLoginId;

    private Long employeeIdentificationNumber;

    private String password;

    public AdminRequestDto() {
    }

    public AdminRequestDto(String name, String socialLoginId,
                           Long employeeIdentificationNumber, String password) {
        this.name = name;
        this.socialLoginId = socialLoginId;
        this.employeeIdentificationNumber = employeeIdentificationNumber;
        this.password = password;
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
}
