package org.kainos.ea.exception;

public class InvalidBandException extends Throwable {
    public InvalidBandException(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return "Invalid band exception";
    }
}
