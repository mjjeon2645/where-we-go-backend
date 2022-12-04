package kr.megaptera.wherewego.exceptions;

public class ExistAdminMemberException extends RuntimeException {
    public ExistAdminMemberException() {
        super("이미 어드민 권한을 가진 사원입니다. 로그인을 진행해주세요");
    }
}
