package kr.megaptera.wherewego.dtos;

public class DeleteUserRequestDto {
    private String reason;

    private String password;

    public DeleteUserRequestDto() {
    }

    public DeleteUserRequestDto(String reason, String password) {
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
