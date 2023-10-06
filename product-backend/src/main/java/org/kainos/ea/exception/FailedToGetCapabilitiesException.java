package org.kainos.ea.exception;

public class FailedToGetCapabilitiesException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to get capabilities from the database";
    }
}
