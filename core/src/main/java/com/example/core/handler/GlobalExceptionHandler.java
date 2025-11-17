package com.example.core.handler;


import com.example.core.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundByIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNotFoundException(NotFoundByIdException ex) {
        return new ErrorResponse("Entity not found", ex.getMessage());
    }

    @ExceptionHandler(InvalidValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalidValueException(InvalidValueException ex) {
        return new ErrorResponse("Invalid value", ex.getMessage());
    }

    @ExceptionHandler(NotFoundResourceByIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleNotFoundException(NotFoundResourceByIdException ex) {
        return new ErrorResponse("Resource not found", ex.getMessage());
    }

    @ExceptionHandler(ResourceAlreadyCreatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleNotFoundException(ResourceAlreadyCreatedException ex) {
        return new ErrorResponse("Resource already created", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorResponse(error.getField(), error.getDefaultMessage()))
                .toList();
    }

    @ExceptionHandler(FileException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleFileException(FileException ex) {
        return new ErrorResponse("File error", ex.getMessage());
    }
}
