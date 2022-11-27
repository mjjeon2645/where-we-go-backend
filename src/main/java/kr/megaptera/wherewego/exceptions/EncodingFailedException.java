package kr.megaptera.wherewego.exceptions;

public class EncodingFailedException extends RuntimeException {
    public EncodingFailedException() {
        super("검색어 인코딩에 실패했습니다");
    }
}
