package kr.megaptera.wherewego.errorDtos;

public class CategoryNotSelectedErrorDto extends ErrorDto {
    public CategoryNotSelectedErrorDto() {
        super(1102, "가고싶은 장소의 유형을 선택해주세요!");
    }
}
