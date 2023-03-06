package com.flexpag.paymentscheduler.Controller;

import com.flexpag.paymentscheduler.Model.PaymentScheduleModel;
import com.flexpag.paymentscheduler.Service.PaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("payments")
public class PaymentSchedulerController {

    @Autowired
    private PaymentScheduleService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentScheduleModel>> findAll(){
        List<PaymentScheduleModel> model = paymentService.findAll();
        if (model.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(paymentService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PaymentScheduleModel> findBydId(@PathVariable("id") Long id){
        PaymentScheduleModel paymentModel = paymentService.findById(id);
        return new ResponseEntity<>(paymentModel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaymentScheduleModel> createPayment (@RequestBody PaymentScheduleModel model){
        return new ResponseEntity<>(paymentService.savePayment(model), HttpStatus.CREATED);
    }

}
