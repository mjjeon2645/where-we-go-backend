package kr.megaptera.wherewego.exceptions;

public class ConnectionFailedException extends RuntimeException {
    public ConnectionFailedException() {
        super("연결이 실패했습니다.");
    }
}
