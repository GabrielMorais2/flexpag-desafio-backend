package com.flexpag.paymentscheduler.Service;

import com.flexpag.paymentscheduler.Model.Enum.PaymentStatus;
import com.flexpag.paymentscheduler.Model.PaymentScheduleModel;
import com.flexpag.paymentscheduler.Repository.PaymentScheduleRepository;
import com.flexpag.paymentscheduler.exeception.EntityDeleteException;
import com.flexpag.paymentscheduler.exeception.EntityUpdateException;
import com.flexpag.paymentscheduler.exeception.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentScheduleService {

    private final PaymentScheduleRepository paymentScheduleRepository;

    public PaymentScheduleService(PaymentScheduleRepository paymentScheduleRepository) {
        this.paymentScheduleRepository = paymentScheduleRepository;
    }

    public List<PaymentScheduleModel> findAll() {
        return paymentScheduleRepository.findAll();
    }

    @Transactional
    public PaymentScheduleModel savePayment(PaymentScheduleModel paymentModel) {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime paymentDate = paymentModel.getDate();
        BigDecimal paymentAmount = paymentModel.getPayment();

        if (paymentDate.isBefore(currentDate)) {
            throw new ValidationException("The payment date cannot be in the past.");
        }

        if (paymentAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("The payment amount must be greater than zero.");
        }

        return paymentScheduleRepository.save(paymentModel);
    }

    public PaymentScheduleModel findById(Long id) {
        return paymentScheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));
    }

    @Transactional
    public void deletePaymentById(Long id) {
        PaymentScheduleModel payment = paymentScheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));

        if (payment.getPaymentStatus() == PaymentStatus.PAID) {
            throw new EntityDeleteException("Cannot remove payment with PAID status.");
        }

        paymentScheduleRepository.deleteById(id);
    }

    @Transactional
    public PaymentScheduleModel updatePaymentById(Long id, PaymentScheduleModel updatedPayment) {
        PaymentScheduleModel payment = paymentScheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));

        if (payment.getPaymentStatus() == PaymentStatus.PAID) {
            throw new EntityUpdateException("Cannot update payment with PAID status.");
        }

        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime updatedPaymentDate = updatedPayment.getDate();

        if (updatedPaymentDate.isBefore(currentDate)) {
            throw new EntityUpdateException("The payment date cannot be in the past.");
        }

        payment.setDate(updatedPaymentDate);
        paymentScheduleRepository.save(payment);

        return payment;
    }
}