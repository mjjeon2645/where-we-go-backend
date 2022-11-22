package kr.megaptera.wherewego.exceptions;

public class NicknameDuplicatedException extends RuntimeException {
    public NicknameDuplicatedException() {
        super("이미 사용중인 닉네임입니다. 다른 닉네임을 입력해주세요");
    }
}
