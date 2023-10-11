package org.kainos.ea.exception;

public class FailedToRegisterUserException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to register user";
    }
}
