package kr.megaptera.wherewego.exceptions;

public class AuthenticationError extends RuntimeException {
    public AuthenticationError() {
        super("Authentication Error");
    }
}
