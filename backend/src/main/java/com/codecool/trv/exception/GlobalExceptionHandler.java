package com.codecool.trv.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    //TODO implement custom ApiError object in order to implement proper error handling towards the client

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleElementNotFound(ResourceNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> handleRecordNotFound(EmptyResultDataAccessException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        if (exception.getCause() instanceof ConstraintViolationException) {
            return new ResponseEntity<>("Database conflict", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Server issue", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
