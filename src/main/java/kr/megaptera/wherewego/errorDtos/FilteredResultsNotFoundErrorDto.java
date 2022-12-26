package kr.megaptera.wherewego.errorDtos;

public class FilteredResultsNotFoundErrorDto extends ErrorDto {
    public FilteredResultsNotFoundErrorDto() {
        super(1103, "검색 결과가 없습니다(알 수 없는 에러)");
    }
}
