package kr.megaptera.wherewego.errorDtos;

public abstract class ErrorDto {
    private int code;
    private String message;

    public ErrorDto() {
    }

    public ErrorDto(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
