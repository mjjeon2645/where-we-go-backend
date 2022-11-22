package kr.megaptera.wherewego.exceptions;

public class PlaceNotFoundException extends RuntimeException {
    public PlaceNotFoundException() {
        super("장소를 찾을수가 없습니다");
    }
}
