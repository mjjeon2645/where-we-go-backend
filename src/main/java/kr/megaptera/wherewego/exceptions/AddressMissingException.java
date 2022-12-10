package kr.megaptera.wherewego.exceptions;

public class AddressMissingException extends RuntimeException {
    public AddressMissingException() {
        super("주소 찾기를 진행해주세요");
    }
}
