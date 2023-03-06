package com.flexpag.paymentscheduler.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.flexpag.paymentscheduler.Model.Enum.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "payment")
@Table(name = "payments")
@AllArgsConstructor
@Builder
public class PaymentScheduleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private LocalDateTime date;
    private BigDecimal payment;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    public PaymentScheduleModel() {
        setPaymentStatus(PaymentStatus.PENDING);
    }

    public PaymentScheduleModel(LocalDateTime date, BigDecimal payment) {
        this.date = date;
        this.payment = payment;
        this.paymentStatus = PaymentStatus.PENDING;
    }
}
