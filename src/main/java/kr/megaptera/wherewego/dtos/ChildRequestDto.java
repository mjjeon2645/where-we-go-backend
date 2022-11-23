package kr.megaptera.wherewego.dtos;

public class ChildRequestDto {
    private String birthday;

    private String gender;

    public ChildRequestDto() {
    }

    public ChildRequestDto(String birthday, String gender) {
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
