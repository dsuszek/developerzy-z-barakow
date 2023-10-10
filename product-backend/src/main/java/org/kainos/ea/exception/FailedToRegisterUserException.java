package org.kainos.ea.exception;

public class FailedToRegisterUserException extends Throwable {
    public FailedToRegisterUserException(String s) {
        super(s);
    }
    @Override
    public String getMessage() {
        return "Failed to register user";
    }
}
