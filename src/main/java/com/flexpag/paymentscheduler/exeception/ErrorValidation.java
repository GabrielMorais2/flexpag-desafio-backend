package com.flexpag.paymentscheduler.exeception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorValidation {

    private int statusCode;
    private LocalDateTime data;
    private String campo;
    private String mensagem;

    public ErrorValidation(int statusCode, LocalDateTime date, String message) {
        this.statusCode = statusCode;
        this.data = date;
        this.mensagem = message;
    }
}
