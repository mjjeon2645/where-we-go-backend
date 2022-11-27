package kr.megaptera.wherewego.dtos;

public class TrialLoginRequestDto {
    private String trialId;

    private String password;

    public TrialLoginRequestDto() {
    }

    public TrialLoginRequestDto(String trialId, String password) {
        this.trialId = trialId;
        this.password = password;
    }

    public String getTrialId() {
        return trialId;     
    }

    public String getPassword() {
        return password;
    }
}
