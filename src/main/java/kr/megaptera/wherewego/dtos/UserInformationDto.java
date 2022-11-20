package kr.megaptera.wherewego.dtos;

public class UserInformationDto {
    private Long id;

    private String email;

    private String nickname;

    private String authBy;

    public UserInformationDto() {
    }

    public UserInformationDto(Long id, String email, String nickname, String authBy) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.authBy = authBy;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAuthBy() {
        return authBy;
    }
}
