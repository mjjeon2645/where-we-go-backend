package kr.megaptera.wherewego.errorDtos;

public class CategoryMissingErrorDto extends ErrorDto{
    public CategoryMissingErrorDto() {
        super(7006, "장소 유형을 선택해주세요");
    }
}
