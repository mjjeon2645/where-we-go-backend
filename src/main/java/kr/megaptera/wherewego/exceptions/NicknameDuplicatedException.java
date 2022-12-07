package kr.megaptera.wherewego.exceptions;

public class NicknameDuplicatedException extends RuntimeException {
    public NicknameDuplicatedException() {
        super("이미 다른 분이 사용중이예요");
    }
}
