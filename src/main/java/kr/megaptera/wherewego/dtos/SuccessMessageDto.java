package kr.megaptera.wherewego.dtos;

public class SuccessMessageDto {
    private String message;

    public SuccessMessageDto() {
    }

    public SuccessMessageDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
