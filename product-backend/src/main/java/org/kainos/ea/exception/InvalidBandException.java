package org.kainos.ea.exception;

public class InvalidBandException extends Throwable {
    @Override
    public String getMessage() {
        return "Invalid band exception";
    }
}
