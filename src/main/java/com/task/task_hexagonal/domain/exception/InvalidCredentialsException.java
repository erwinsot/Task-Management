package com.task.task_hexagonal.domain.exception;

public class InvalidCredentialsException extends RuntimeException{
    public  InvalidCredentialsException(String message){
        super(message);
    }
}
