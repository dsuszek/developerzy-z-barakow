package org.kainos.ea.exception;

public class FailedToCreateBandException extends Throwable {

    @Override
    public String getMessage() {
        return "Failed to create band";
    }
}
