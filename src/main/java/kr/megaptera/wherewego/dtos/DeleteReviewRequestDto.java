package kr.megaptera.wherewego.dtos;

public class DeleteReviewRequestDto {
    private String password;

    private String reason;

    public DeleteReviewRequestDto() {
    }

    public DeleteReviewRequestDto(String password, String reason) {
        this.password = password;
        this.reason = reason;
    }

    public String getPassword() {
        return password;
    }

    public String getReason() {
        return reason;
    }
}
