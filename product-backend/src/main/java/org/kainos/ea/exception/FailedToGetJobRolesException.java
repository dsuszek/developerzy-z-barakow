package org.kainos.ea.exception;

public class FailedToGetJobRolesException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to get job roles from the database";
    }
}
