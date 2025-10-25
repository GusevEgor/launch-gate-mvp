package com.example.core.exception;

public class InvalidValueException extends RuntimeException {
    public <T> InvalidValueException(Class<T> clazz) {
        super(clazz.getSimpleName());
    }
}