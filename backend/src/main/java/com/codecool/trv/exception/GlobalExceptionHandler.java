package com.codecool.trv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    /*@ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleElementNotFound(NoSuchElementException exception) {
        return ResponseEntity.notFound().build();
    }*/

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleElementNotFound(ResourceNotFoundException exception) {
        //ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
        //return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
