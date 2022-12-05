package kr.megaptera.wherewego.exceptions;

public class EmptyReasonException extends RuntimeException {
    public EmptyReasonException() {
        super("사유를 입력해주세요");
    }
}
