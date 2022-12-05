package kr.megaptera.wherewego.exceptions;

public class AdminPasswordError extends RuntimeException {
    public AdminPasswordError() {
        super("비밀번호가 틀립니다. 다시 확인해주세요");
    }
}
