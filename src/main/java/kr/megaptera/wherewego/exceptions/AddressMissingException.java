package kr.megaptera.wherewego.exceptions;

public class AddressMissingException extends RuntimeException {
    public AddressMissingException() {
        super("주소를 입력해주세요");
    }
}
