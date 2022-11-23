package kr.megaptera.wherewego.dtos;

public class ChildDto {
    private Long id;

    private Long userId;

    private String gender;

    private String birthday;

    public ChildDto() {
    }

    public ChildDto(Long id, Long userId, String gender, String birthday) {
        this.id = id;
        this.userId = userId;
        this.gender = gender;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }
}
