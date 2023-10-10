package org.kainos.ea.exception;

public class RoleDoesNotExistException extends Throwable {
    @Override
    public String getMessage() {
        return "Role does not exist";
    }
}