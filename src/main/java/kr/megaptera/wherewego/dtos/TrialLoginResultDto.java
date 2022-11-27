package kr.megaptera.wherewego.dtos;

public class TrialLoginResultDto {
    private Long trialId;

    private String accessToken;

    private String nickname;

    private String state;

    public TrialLoginResultDto() {
    }

    public TrialLoginResultDto(Long trialId, String accessToken, String nickname, String state) {
        this.trialId = trialId;
        this.accessToken = accessToken;
        this.nickname = nickname;
        this.state = state;
    }

    public Long getTrialId() {
        return trialId;
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
