package org.kainos.ea.exception;

public class InvalidUserRegistrationRequestException extends Throwable {
    @Override
    public String getMessage() {
        return "Invalid user registration request";
    }
}
