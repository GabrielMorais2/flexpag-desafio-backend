package com.flexpag.paymentscheduler.Controller;

import com.flexpag.paymentscheduler.Model.PaymentScheduleModel;
import com.flexpag.paymentscheduler.Service.PaymentScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("payments")
public class PaymentScheduleController {

    private final PaymentScheduleService paymentService;

    public PaymentScheduleController(PaymentScheduleService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentScheduleModel>> findAll() {
        List<PaymentScheduleModel> paymentScheduleModels = paymentService.findAll();
        if (paymentScheduleModels.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(paymentScheduleModels);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PaymentScheduleModel> findById(@PathVariable("id") Long id) {
        PaymentScheduleModel paymentModel = paymentService.findById(id);
        return ResponseEntity.ok(paymentModel);
    }

    @PostMapping
    public ResponseEntity<PaymentScheduleModel> createPayment(@RequestBody PaymentScheduleModel paymentModel) {
        PaymentScheduleModel createdPayment = paymentService.savePayment(paymentModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        paymentService.deletePaymentById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PaymentScheduleModel> update(@PathVariable("id") Long id,
                                                       @RequestBody PaymentScheduleModel paymentUpdate) {
        PaymentScheduleModel updatedPayment = paymentService.updatePaymentById(id, paymentUpdate);
        return ResponseEntity.ok(updatedPayment);
    }
}
