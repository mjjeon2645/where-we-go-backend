package kr.megaptera.wherewego.exceptions;

public class ChildInformationMissingException extends RuntimeException {
    public ChildInformationMissingException() {
        super("모든 항목을 정확히 입력해주세요");
    }
}
