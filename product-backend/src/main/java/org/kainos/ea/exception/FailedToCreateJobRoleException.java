package org.kainos.ea.exception;

public class FailedToCreateJobRoleException extends Throwable {

    public FailedToCreateJobRoleException(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return "Failed to create job role";
    }
}
