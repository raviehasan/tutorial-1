package id.ac.ui.cs.advprog.eshop.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;

@Repository
public class PaymentRepository {
    private List<Payment> payments = new ArrayList<>();

    public Payment save(Payment toBeSavedPayment) {
        for (Payment payment : payments) {
            if (payment.getId().equals(toBeSavedPayment.getId())) {
                throw new IllegalArgumentException("Payment already exists");
            }
        }
        payments.add(toBeSavedPayment);
        return toBeSavedPayment;
    }

    public Payment findById(String paymentId) {
        for (Payment payment : payments) {
            if (payment.getId().equals(paymentId))
                return payment;
        }
        return null;
    }

    public List<Payment> getAllPayments() {
        return payments;
    }
}