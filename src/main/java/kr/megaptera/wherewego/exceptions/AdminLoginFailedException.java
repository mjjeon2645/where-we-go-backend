package kr.megaptera.wherewego.exceptions;

public class AdminLoginFailedException extends RuntimeException{
    public AdminLoginFailedException() {
        super("입력하신 정보가 정확하지 않습니다. 아이디와 비밀번호를 다시 확인해주세요.");
    }
}
