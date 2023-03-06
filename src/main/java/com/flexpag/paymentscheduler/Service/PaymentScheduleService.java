package com.flexpag.paymentscheduler.Service;

import com.flexpag.paymentscheduler.Model.PaymentScheduleModel;
import com.flexpag.paymentscheduler.Repository.PaymentScheduleRepository;
import com.flexpag.paymentscheduler.exeception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentScheduleService {

    @Autowired
    private PaymentScheduleRepository repository;

    public List<PaymentScheduleModel> findAll() {
        return repository.findAll();
    }

    @Transactional
    public PaymentScheduleModel savePayment(PaymentScheduleModel paymentModel) {
        if (paymentModel.getDate().isBefore(LocalDateTime.now())) {
            throw new ValidationException("The date cannot be retroactive");
        }

        if (paymentModel.getPayment().compareTo(BigDecimal.ZERO) < 0 || paymentModel.getPayment().compareTo(BigDecimal.ZERO) == 0) {
            throw new ValidationException("Payout cannot be less than 0 or equal to 0.");
        }

        return repository.save(paymentModel);
    }
    public PaymentScheduleModel findById(Long id){
        if(repository.findById(id).isEmpty()){
            throw new EntityNotFoundException("schedule not found");
        }
        return repository.findById(id).get();
    }
}
