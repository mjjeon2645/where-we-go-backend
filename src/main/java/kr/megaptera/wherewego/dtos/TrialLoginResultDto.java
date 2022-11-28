package kr.megaptera.wherewego.dtos;

public class TrialLoginResultDto {
    private String accessToken;

    private String nickname;

    private String state;

    public TrialLoginResultDto() {
    }

    public TrialLoginResultDto(String accessToken, String nickname, String state) {
        this.accessToken = accessToken;
        this.nickname = nickname;
        this.state = state;
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
