package com.flexpag.paymentscheduler.Service;

import com.flexpag.paymentscheduler.Model.Enum.PaymentStatus;
import com.flexpag.paymentscheduler.Model.PaymentScheduleModel;
import com.flexpag.paymentscheduler.Repository.PaymentScheduleRepository;
import com.flexpag.paymentscheduler.exeception.EntityDeleteException;
import com.flexpag.paymentscheduler.exeception.EntityUpdateException;
import com.flexpag.paymentscheduler.exeception.ValidationException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentScheduleService {

    private final PaymentScheduleRepository paymentScheduleRepository;

    @Autowired
    public PaymentScheduleService(PaymentScheduleRepository paymentScheduleRepository) {
        this.paymentScheduleRepository = paymentScheduleRepository;
    }

    /*
     * Retorna a lista de pagamentos agendados com um determinado status.
     * Se o status for nulo, retorna todos os pagamentos agendados.
     *
     * @param status O status dos pagamentos a serem retornados
     * @return A lista de pagamentos agendados com o status especificado
     */
    public List<PaymentScheduleModel> findAll(PaymentStatus status) {
        List<PaymentScheduleModel> paymentList = status != null ? paymentScheduleRepository.findAllByPaymentStatus(status)
                : paymentScheduleRepository.findAll();

        return paymentList;
    }

    /*
     * Retorna um pagamento agendado com um determinado ID.
     *
     * @param id O ID do pagamento agendado a ser retornado
     * @return O pagamento agendado com o ID especificado
     * @throws EntityNotFoundException Se o ID especificado não existir
     */
    public PaymentScheduleModel findById(@NonNull Long id) throws EntityNotFoundException {
        PaymentScheduleModel payment = paymentScheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));

        return payment;
    }

    /*
     * Salva um novo pagamento agendado e valida os dados inseridos.
     *
     * @param paymentModel O pagamento agendado a ser salvo
     * @return O pagamento agendado salvo
     * @throws ValidationException Se algum dos dados inseridos for inválido
     */
    @Transactional
    public PaymentScheduleModel savePayment(@NonNull PaymentScheduleModel paymentModel) throws ValidationException {
        validatePayment(paymentModel);
        return paymentScheduleRepository.save(paymentModel);
    }

    /*
     * Exclui um pagamento agendado com um determinado ID.
     *
     * @param id O ID do pagamento agendado a ser excluído
     * @throws EntityNotFoundException Se o ID especificado não existir
     * @throws EntityDeleteException   Se o pagamento agendado tiver o status "PAID"
     */
    @Transactional
    public void deletePaymentById(@NonNull Long id) throws EntityNotFoundException, EntityDeleteException {
        PaymentScheduleModel payment = paymentScheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));

        if (payment.getPaymentStatus() == PaymentStatus.PAID) {
            throw new EntityDeleteException("Cannot remove payment with PAID status.");
        }

        paymentScheduleRepository.deleteById(id);
    }

    /*
     * Método responsável por atualizar um pagamento pelo seu id.
     *
     * @param id Identificador do pagamento a ser atualizado.
     * @param updatedPayment Instância atualizada do pagamento.
     * @return Instância atualizada do pagamento.
     * @throws EntityNotFoundException Exceção lançada quando o pagamento não é encontrado.
     * @throws EntityUpdateException Exceção lançada quando o pagamento já foi pago ou a data de pagamento está no passado.
     */
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

    /*
     *
     * Método responsável por verificar se existem pagamentos pendentes que precisam ser pagos.
     *
     * Executa diariamente à meia-noite no fuso horário America/Sao_Paulo.
     */
    @Scheduled(cron = "0 0 0 * * *",  zone = "America/Sao_Paulo") // Executa todos os dias à meia-noite
    @Transactional
    public void checkPayments() {
        List<PaymentScheduleModel> paymentList = paymentScheduleRepository.findAllByPaymentStatus(PaymentStatus.PENDING);

        paymentList.stream().filter(payment -> payment.getDate().isBefore(LocalDateTime.now()))
                .forEach(payment -> {
                    payment.setPaymentStatus(PaymentStatus.PAID);
                    paymentScheduleRepository.save(payment);
                });
    }

    /*
     * Método responsável por validar um pagamento antes de salvá-lo no banco de dados.
     *
     * @param paymentModel Instância do pagamento a ser validada.
     * @throws IllegalArgumentException Exceção lançada quando a data de pagamento é anterior à data atual ou o valor do pagamento é menor ou igual a zero.
     */
    private void validatePayment(PaymentScheduleModel paymentModel) {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime paymentDate = paymentModel.getDate();
        BigDecimal paymentAmount = paymentModel.getAmount();

        if (paymentDate.isBefore(currentDate)) {
            throw new IllegalArgumentException("The payment date cannot be in the past.");
        }

        if (paymentAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("The payment amount must be greater than zero.");
        }
    }

}