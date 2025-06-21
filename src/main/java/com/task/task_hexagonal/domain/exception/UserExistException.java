package com.task.task_hexagonal.domain.exception;

public class UserExistException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public UserExistException(String message) {
        super(message);
    }

    public UserExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
