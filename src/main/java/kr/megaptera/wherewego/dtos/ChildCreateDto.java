package kr.megaptera.wherewego.dtos;

import javax.validation.constraints.*;

public class ChildCreateDto {
    @NotBlank(message = "모든 항목을 정확히 입력해주세요!")
    private String birthday;

    @NotBlank(message = "모든 항목을 정확히 입력해주세요!")
    private String gender;

    public ChildCreateDto() {
    }

    public ChildCreateDto(String birthday, String gender) {
        this.birthday = birthday;
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }
}
