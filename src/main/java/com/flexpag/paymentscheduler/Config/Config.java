package com.flexpag.paymentscheduler.Config;

import com.flexpag.paymentscheduler.Model.PaymentScheduleModel;
import com.flexpag.paymentscheduler.Repository.PaymentScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class Config implements CommandLineRunner {

    @Autowired
    private PaymentScheduleRepository paymentRepository;

    @Override
    public void run(String... args) throws Exception {
        PaymentScheduleModel payment1 = new PaymentScheduleModel(LocalDateTime.now().plusDays(3), new BigDecimal(300));
        PaymentScheduleModel payment2 = new PaymentScheduleModel(LocalDateTime.now().plusDays(5), new BigDecimal(500));
        PaymentScheduleModel payment3 = new PaymentScheduleModel(LocalDateTime.now().plusDays(7), new BigDecimal(700));

        paymentRepository.saveAll(Arrays.asList(payment1, payment2, payment3));
    }

}