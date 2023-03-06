package com.flexpag.paymentscheduler.Repository;

import com.flexpag.paymentscheduler.Model.Enum.PaymentStatus;
import com.flexpag.paymentscheduler.Model.PaymentScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentScheduleRepository extends JpaRepository<PaymentScheduleModel, Long> {
    List<PaymentScheduleModel> findAllByPaymentStatus(PaymentStatus status);
}
