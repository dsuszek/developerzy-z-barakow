package org.kainos.ea.exception;

public class FailedToRegisterUserException extends Throwable {

    public FailedToRegisterUserException() {}

    public FailedToRegisterUserException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Failed to register user";
    }
}
