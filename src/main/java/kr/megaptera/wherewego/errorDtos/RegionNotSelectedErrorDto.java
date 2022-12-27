package kr.megaptera.wherewego.errorDtos;

public class RegionNotSelectedErrorDto extends ErrorDto {
    public RegionNotSelectedErrorDto() {
        super(1101, "가고싶은 지역을 선택해주세요!");
    }
}
