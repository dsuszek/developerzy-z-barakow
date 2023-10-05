package org.kainos.ea.exception;

public class InvalidJobRoleException extends Throwable {
    public InvalidJobRoleException(String error) {
        super(error);
    }

    @Override
    public String getMessage() {
        return "Invalid job role exception";
    }
}
