package com.flexpag.paymentscheduler.exeception;

public class EntityDeleteException extends RuntimeException {
    public EntityDeleteException(String message){
        super(message);
    }
}
