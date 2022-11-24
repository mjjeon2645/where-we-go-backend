package kr.megaptera.wherewego.dtos;

public class LoginResultDto {
    private Long userId;

    private String accessToken;

    private String nickname;

    private String state;

    public LoginResultDto() {
    }

    public LoginResultDto(Long userId, String accessToken, String nickname, String state) {
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

    @Override
    public boolean equals(Object other) {
        LoginResultDto otherLoginResultDto = (LoginResultDto) other;

        return accessToken.equals(otherLoginResultDto.getAccessToken())
            && nickname.equals(otherLoginResultDto.getNickname())
            && state.equals(otherLoginResultDto.getState());
    }

    @Override
    public String toString() {
        return "userId : " + userId + " accessToken : " + accessToken +
            " nickname : " + nickname + " state : " + state;
    }
}
