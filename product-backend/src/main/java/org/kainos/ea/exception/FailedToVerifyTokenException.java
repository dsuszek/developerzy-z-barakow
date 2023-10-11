package org.kainos.ea.exception;

public class FailedToVerifyTokenException extends Throwable {
    public FailedToVerifyTokenException() {}

    public FailedToVerifyTokenException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Failed to verify token";
    }
}
