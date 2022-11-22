package kr.megaptera.wherewego.exceptions;

public class UnchangedNicknameException extends RuntimeException{
    public UnchangedNicknameException() {
        super("현재 사용하고 계시는 닉네임과 동일합니다. 다른 닉네임을 입력해주세요");
    }
}
