package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Payment {
    String id;
    String method;
    Order order;
    Map<String, String> paymentData;
    String status;

    public Payment(String id, String method, Order order, Map<String, String> paymentData, String status) {
        this.id = id;
        this.method = method;
        this.setOrder(order);
        this.setPaymentData(paymentData);
        this.setStatus(status);
    }

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this(id, method, order, paymentData, PaymentStatus.PENDING.getValue());
    }

    private void setOrder(Order order) {
        if (order == null)
            throw new IllegalArgumentException("Order must not be null.");

        this.order = order;
    }

    void setPaymentData(Map<String, String> paymentData) {
        if (PaymentMethod.contains(this.getMethod()))
            throw new IllegalArgumentException("Method specific payment data is forbidden.");

        this.paymentData = null;
    }

    public void setStatus(String status) {
        if (!PaymentStatus.contains(status))
            throw new IllegalArgumentException("Payment status is invalid.");

        this.status = status;
    }
}