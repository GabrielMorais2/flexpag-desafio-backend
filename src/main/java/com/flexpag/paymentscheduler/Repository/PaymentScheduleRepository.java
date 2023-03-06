package com.flexpag.paymentscheduler.Repository;

import com.flexpag.paymentscheduler.Model.PaymentScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentScheduleRepository extends JpaRepository<PaymentScheduleModel, Long> {
}
