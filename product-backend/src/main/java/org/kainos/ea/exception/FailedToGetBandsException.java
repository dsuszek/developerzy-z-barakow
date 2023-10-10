package org.kainos.ea.exception;

public class FailedToGetBandsException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to get bands from the database";
    }
}
