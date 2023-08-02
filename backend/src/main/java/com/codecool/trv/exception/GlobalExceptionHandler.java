package com.codecool.trv.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleElementNotFound(ResourceNotFoundException exception) {
        //ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
        //return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> handleRecordNotFound(EmptyResultDataAccessException exception) {
        //RestApiError error = new RestApiError(HttpStatus.BAD_REQUEST, Map.of("message", "Record not found"), request.getRequestURI());
        //return new ResponseEntity<>(error, error.getHttpStatus());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
