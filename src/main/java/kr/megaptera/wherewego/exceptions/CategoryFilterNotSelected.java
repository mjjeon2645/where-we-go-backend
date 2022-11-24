package kr.megaptera.wherewego.exceptions;

public class CategoryFilterNotSelected extends RuntimeException {
    public CategoryFilterNotSelected() {
        super("가고싶은 장소의 유형을 선택해주세요!");
    }
}
