package com.example.core.exception;

public class NotFoundByIdException extends RuntimeException {
    public <T> NotFoundByIdException(Class<T> clazz, Long id){
        super(clazz.getSimpleName() + " by id=" + id + " not found");
    }
}
