package kr.megaptera.wherewego.dtos;

public class DeletePlaceRequestDto {
    private String reason;

    private String password;

    public DeletePlaceRequestDto() {
    }

    public DeletePlaceRequestDto(String reason, String password) {
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
