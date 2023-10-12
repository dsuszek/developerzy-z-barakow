package org.kainos.ea.exception;

public class FailedToCreateNewCapabilityException extends Throwable {
    @Override
    public String getMessage(){
        return"Failed to create new capability";
    }
}
