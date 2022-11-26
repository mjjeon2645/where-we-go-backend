package kr.megaptera.wherewego.exceptions;

public class ChildNotFoundException extends RuntimeException{
    public ChildNotFoundException() {
        super("아이 정보가 없습니다.");
    }
}
