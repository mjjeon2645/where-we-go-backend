package kr.megaptera.wherewego.exceptions;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException() {
        super("Login Failed!");
    }
}
