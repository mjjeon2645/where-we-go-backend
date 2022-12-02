package kr.megaptera.wherewego.errorDtos;

public class UserReviewNotFoundErrorDto extends ErrorDto {
    public UserReviewNotFoundErrorDto() {
        super(6000, "해당 리뷰를 찾을 수 없습니다");
    }
}
