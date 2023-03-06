package com.flexpag.paymentscheduler.exeception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorValidation {

    private int statusCode;
    private Date data;
    private String campo;
    private String mensagem;

}
