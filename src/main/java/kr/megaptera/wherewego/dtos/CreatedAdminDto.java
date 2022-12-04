package kr.megaptera.wherewego.dtos;

public class CreatedAdminDto {
    private String socialLoginId;

    private String name;

    private Long employeeIdentificationNumber;

    public CreatedAdminDto() {
    }

    public CreatedAdminDto(String socialLoginId, String name, Long employeeIdentificationNumber) {
        this.socialLoginId = socialLoginId;
        this.name = name;
        this.employeeIdentificationNumber = employeeIdentificationNumber;
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
}
