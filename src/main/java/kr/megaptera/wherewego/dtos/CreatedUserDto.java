package kr.megaptera.wherewego.dtos;

public class CreatedUserDto {
    private Long userId;

    private String accessToken;

    private String nickname;

    private String state;

    public CreatedUserDto() {
    }

    public CreatedUserDto(Long userId, String accessToken, String nickname, String state) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.nickname = nickname;
        this.state = state;
    }

    public Long getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getNickname() {
        return nickname;
    }

    public String getState() {
        return state;
    }
}
