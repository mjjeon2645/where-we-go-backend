package kr.megaptera.wherewego.exceptions;

public class RegionNotSelected extends RuntimeException {
    public RegionNotSelected() {
        super("가고싶은 지역을 선택해주세요!");
    }
}
