package org.kainos.ea.exception;

public class FailedToCreateBandException extends Throwable {

    public FailedToCreateBandException(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return "Failed to create band";
    }
}
