package kr.megaptera.wherewego.exceptions;

public class RegionFilterNotSelected extends RuntimeException {
    public RegionFilterNotSelected() {
        super("가고싶은 지역을 선택해주세요!");
    }
}
