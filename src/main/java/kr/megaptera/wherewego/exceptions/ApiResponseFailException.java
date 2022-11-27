package kr.megaptera.wherewego.exceptions;

public class ApiResponseFailException extends RuntimeException {
    public ApiResponseFailException() {
        super("API 응답을 읽는 데 실패했습니다.");
    }
}
