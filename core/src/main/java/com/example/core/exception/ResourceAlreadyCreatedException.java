package com.example.core.exception;

public class ResourceAlreadyCreatedException extends RuntimeException {
    public <T> ResourceAlreadyCreatedException(Class<T> clazz, Long id){
        super(clazz.getSimpleName() + " by id=" + id + " already created");
    }
}
