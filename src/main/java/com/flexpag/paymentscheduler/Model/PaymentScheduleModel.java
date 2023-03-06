package com.flexpag.paymentscheduler.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.flexpag.paymentscheduler.Model.Enum.PaymentStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "payment")
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentScheduleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "date cannot be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private LocalDateTime date;

    @DecimalMin("0.0")
    @NotNull(message = "Amount cannot be empty")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    public PaymentScheduleModel(LocalDateTime date, BigDecimal amount) {
        this.date = date;
        this.amount = amount;
    }
}
