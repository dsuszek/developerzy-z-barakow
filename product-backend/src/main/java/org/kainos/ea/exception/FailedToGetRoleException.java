package org.kainos.ea.exception;

public class FailedToGetRoleException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to get role from the database";
    }
}
