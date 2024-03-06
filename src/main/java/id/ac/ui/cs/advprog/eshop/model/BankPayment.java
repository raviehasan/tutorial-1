package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;
import lombok.Getter;

@Getter
public class BankPayment extends Payment {
    public BankPayment(String id, String method, Order order, Map<String, String> paymentData, String status) {
        super(id, method, order, paymentData, status);
    }

    public BankPayment(String id, String method, Order order, Map<String, String> paymentData) {
        super(id, method, order, paymentData);
    }

    @Override
    protected void setPaymentData(Map<String, String> paymentData) {
        if (paymentData.isEmpty())
            throw new IllegalArgumentException("Payment data must not be empty.");

        if (paymentData.get("bankName").isEmpty())
            throw new IllegalArgumentException("Bank name must not be empty.");

        if (paymentData.get("referenceCode").isEmpty())
            throw new IllegalArgumentException("Reference code must not be empty.");

        this.paymentData = paymentData;
    }
}