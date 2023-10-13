package org.kainos.ea.exception;

public class InvalidJobRoleException extends Throwable {
    @Override
    public String getMessage() {
        return "Invalid job role exception";
    }
}
