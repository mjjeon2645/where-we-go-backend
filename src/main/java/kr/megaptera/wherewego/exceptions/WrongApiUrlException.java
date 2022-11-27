package kr.megaptera.wherewego.exceptions;

import java.net.*;

public class WrongApiUrlException extends RuntimeException {
    public WrongApiUrlException() {
        super("API URL이 잘못되었습니다.");
    }
}
