package org.kainos.ea.exception;

public class TokenExpiredException extends Throwable {
    public TokenExpiredException(String s) {
        super(s);
    }
    @Override
    public String getMessage() {
        return "Token has expired";
    }
}
