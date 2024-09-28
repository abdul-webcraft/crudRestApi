package com.restAPI.exception;

public class UniqueConstraintException extends Exception{
    public UniqueConstraintException(String message) {
        super(message);
    }
}
