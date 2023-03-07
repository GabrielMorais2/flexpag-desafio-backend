package com.flexpag.paymentscheduler.Controller;

import com.flexpag.paymentscheduler.Model.Enum.PaymentStatus;
import com.flexpag.paymentscheduler.Model.PaymentScheduleModel;
import com.flexpag.paymentscheduler.Service.PaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("payments")
public class PaymentScheduleController {

    private final PaymentScheduleService paymentService;
    @Autowired
    public PaymentScheduleController(PaymentScheduleService paymentService) {
        this.paymentService = paymentService;
    }
    /*
     * Encontra todos os pagamentos podendo ser filtrado por status ou não.
     *
     * @param status filtro do status (optional)
     * @return retorna uma lista de pagamentos. Caso a lista esteja vazia, retorna uma resposta sem conteúdo.
     */
    @GetMapping
    public ResponseEntity<List<PaymentScheduleModel>> findAllPayments(@RequestParam(required = false) PaymentStatus status) {
        List<PaymentScheduleModel> payments = paymentService.findAll(status);

        return payments.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(payments);
    }
    /*
     * Encontra um pagamento pelo Id
     *
     * @param id do pagamento
     * @return retorna um pagamento específico com base em um ID passado como parâmetro.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PaymentScheduleModel> findPaymentById(@PathVariable("id") Long id) {
        PaymentScheduleModel payment = paymentService.findById(id);

        return ResponseEntity.ok(payment);
    }
    /*
     * adiciona um novo pagamento.
     *
     * @param payment o objeto PaymentScheduleModel a ser criado
     * @return a ResponseEntity contendo o objeto PaymentScheduleModel criado
     */
    @PostMapping
    public ResponseEntity<PaymentScheduleModel> addPayment(@RequestBody @Valid PaymentScheduleModel payment) {
        PaymentScheduleModel createdPayment = paymentService.savePayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
    }

    /*
     * Delete um pagamento especifico pelo ID.
     *
     * @param id do pagamento
     * @return Retorna um ResponseEntity sem corpo de mensagem em caso de sucesso
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable("id") Long id) {
        paymentService.deletePaymentById(id);
        return ResponseEntity.ok().build();
    }

    /*
     * Atualiza um pagamento pelo id.
     *
     * @param id do pagamento
     * @param paymentUpdate é um objeto de PaymentScheduleModel com os dados atualizados
     * @return a ResponseEntity contendo um PaymentScheduleModel com os dados atualizados
     */
    @PutMapping("/{id}")
    public ResponseEntity<PaymentScheduleModel> updatePayment(@PathVariable("id") Long id,
                                                              @RequestBody PaymentScheduleModel paymentUpdate) {
        PaymentScheduleModel updatedPayment = paymentService.updatePaymentById(id, paymentUpdate);
        return ResponseEntity.ok(updatedPayment);
    }
}
