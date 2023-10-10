package org.kainos.ea.exception;

public class InvalidCapabilityException extends Throwable {
    public InvalidCapabilityException(String error) {
        super(error);
    }
    @Override
    public String getMessage() {
        return "Failed to create capability";
    }
}
