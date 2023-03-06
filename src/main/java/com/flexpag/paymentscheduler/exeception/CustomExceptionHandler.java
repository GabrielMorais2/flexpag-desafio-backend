package com.flexpag.paymentscheduler.exeception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(EntityNotFoundException ex){
        var message = ex.getMessage();
        return new ResponseEntity(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity entityDeleteException(EntityDeleteException ex){
        var message = ex.getMessage();
        return new ResponseEntity(message, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler
    public ResponseEntity entityUpdateException(EntityUpdateException ex){
        var message = ex.getMessage();
        return new ResponseEntity(message, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler
    public ResponseEntity ValidationErro(ValidationException ex){
        var message = ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
