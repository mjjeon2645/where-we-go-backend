package kr.megaptera.wherewego.exceptions;

public class AuthenticationError extends RuntimeException {
    public AuthenticationError() {
        super("접근 권한이 없습니다");
    }
}
