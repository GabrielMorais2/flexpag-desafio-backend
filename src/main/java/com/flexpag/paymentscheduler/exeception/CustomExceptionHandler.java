package com.flexpag.paymentscheduler.exeception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler
    public ResponseEntity ValidationErro(ValidationException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
