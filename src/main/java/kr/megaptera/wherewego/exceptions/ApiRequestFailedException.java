package kr.megaptera.wherewego.exceptions;

public class ApiRequestFailedException extends RuntimeException {
    public ApiRequestFailedException() {
        super("API 요청과 응답 실패");
    }
}
