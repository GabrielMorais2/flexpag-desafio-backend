package com.flexpag.paymentscheduler.exeception;

public class ValidationException extends RuntimeException {

    public ValidationException(String message){
        super(message);
    }

}
