package kr.megaptera.wherewego.exceptions;

public class CategoryMissingException extends RuntimeException {
    public CategoryMissingException() {
        super("장소 유형을 선택해주세요");
    }
}
