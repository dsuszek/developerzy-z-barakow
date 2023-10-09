package org.kainos.ea.exception;

public class FailedToGetBandsException extends Throwable {

    public FailedToGetBandsException(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return "Failed to get bands from the database";
    }
}
