package kr.megaptera.wherewego.exceptions;

public class DuplicateIdentifierException extends RuntimeException {
    public DuplicateIdentifierException() {
        super("이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요");
    }
}
