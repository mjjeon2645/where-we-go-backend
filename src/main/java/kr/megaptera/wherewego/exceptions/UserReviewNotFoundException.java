package kr.megaptera.wherewego.exceptions;

public class UserReviewNotFoundException extends RuntimeException{
    public UserReviewNotFoundException() {
        super("해당 리뷰를 찾을 수 없습니다");
    }
}
