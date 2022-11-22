package kr.megaptera.wherewego.errorDtos;

public class PlaceNotFoundErrorDto extends ErrorDto{
    public PlaceNotFoundErrorDto() {
        super(3001, "장소를 찾을수가 없습니다");
    }
}
