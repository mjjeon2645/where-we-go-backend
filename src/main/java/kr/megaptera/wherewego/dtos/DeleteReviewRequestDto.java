package kr.megaptera.wherewego.dtos;

public class DeleteReviewRequestDto {
    private String reason;

    private String password;


    public DeleteReviewRequestDto() {
    }

    public DeleteReviewRequestDto(String reason, String password) {
        this.reason = reason;
        this.password = password;
    }

    public String getReason() {
        return reason;
    }

    public String getPassword() {
        return password;
    }
}
