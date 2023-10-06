package org.kainos.ea.exception;

public class FailedToGetRolesException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to get roles from the database";
    }
}
