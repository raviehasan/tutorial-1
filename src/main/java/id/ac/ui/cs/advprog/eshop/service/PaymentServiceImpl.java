package id.ac.ui.cs.advprog.eshop.service;

import java.util.UUID;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import id.ac.ui.cs.advprog.eshop.model.BankPayment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.VoucherPayment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> data) {
        Payment payment = null;
        if (method.equals(PaymentMethod.BANK.getValue()))
            payment = createBankPayment(order, method, data);
        else if (method.equals(PaymentMethod.VOUCHER.getValue()))
            payment = createVoucherPayment(order, method, data);

        if (payment == null)
            throw new IllegalArgumentException("Payment method is invalid.");

        paymentRepository.save(payment);
        return payment;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }

    @Override
    public Payment getPayment(String id) {
        return paymentRepository.getPaymentById(id);
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);

        if (paymentRepository.getPaymentById(payment.getId()) == null)
            throw new NoSuchElementException("There is no such payment.");

        if (payment.getStatus().equals(PaymentStatus.SUCCESS.getValue()))
            payment.getOrder().setStatus(OrderStatus.SUCCESS.getValue());
        else if (payment.getStatus().equals(PaymentStatus.REJECTED.getValue()))
            payment.getOrder().setStatus(OrderStatus.FAILED.getValue());
        else
            throw new IllegalArgumentException("Invalid payment status");

        return payment;
    }

    public Payment createBankPayment(Order order, String method, Map<String, String> data) {
        BankPayment bankPayment = new BankPayment(
                UUID.randomUUID().toString(), method, order, data);
        return bankPayment;
    }

    public Payment createVoucherPayment(Order order, String method, Map<String, String> data) {
        VoucherPayment voucherPayment = new VoucherPayment(
                UUID.randomUUID().toString(), method, order, data);
        return voucherPayment;
    }
}