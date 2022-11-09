package kr.megaptera.wherewego.exceptions;

public class SelectedPlaceNotFound extends RuntimeException{
    public SelectedPlaceNotFound() {
        super("선택한 장소의 정보를 불러오지 못했습니다.(알 수 없는 에러)");
    }
}
