package kr.megaptera.wherewego.exceptions;

public class CategoryNotSelected extends RuntimeException {
    public CategoryNotSelected() {
        super("가고싶은 장소의 유형을 선택해주세요!");
    }
}
