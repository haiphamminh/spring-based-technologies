package com.example.exception;

public class NotFoundException extends RuntimeException {
    public static final String ERROR_CODE = "10000";
    public NotFoundException(String message) {
        super(message);
    }
}
