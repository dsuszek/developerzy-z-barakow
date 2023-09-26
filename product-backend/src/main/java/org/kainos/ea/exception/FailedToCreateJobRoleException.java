package org.kainos.ea.exception;

public class FailedToCreateJobRoleException extends Throwable {
    public FailedToCreateJobRoleException() {
    }

    public FailedToCreateJobRoleException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Failed to create job role";
    }
}
