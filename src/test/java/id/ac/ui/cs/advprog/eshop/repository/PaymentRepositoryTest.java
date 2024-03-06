package id.ac.ui.cs.advprog.eshop.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.model.BankPayment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.model.VoucherPayment;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Product> products;
    List<Payment> payments;
    Order order;

    @BeforeEach
    void setup() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();
        products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        Product product2 = new Product();
        product2.setProductId("fb558e9f-1c39-460e-8860-71af6af63bd6");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        products.add(product2);

        order = new Order("gb558e9f-1c39-460e-8860-71af6af63bd6",
                products, 1708560000L, "Safira Sudrajat");

        Payment payment1 = new Payment(
                "hb558e9f-1c39-460e-8860-71af6af63bd6", "",
                order, null);
        Payment payment2 = new Payment(
                "ib558e9f-1c39-460e-8860-71af6af63bd6", "",
                order, null);
        payments.add(payment1);
        payments.add(payment2);

        Map<String, String> voucherPaymentData = new HashMap<>();
        voucherPaymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment voucherPayment = new VoucherPayment(
                "jb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(),
                order, voucherPaymentData);
        payments.add(voucherPayment);

        Map<String, String> bankPaymentData = new HashMap<>();
        bankPaymentData.put("bankName", "BANK_PUSILKOM");
        bankPaymentData.put("referenceCode", "1234567890");
        Payment bankPayment = new BankPayment(
                "kb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.BANK.getValue(),
                order, bankPaymentData);
        payments.add(bankPayment);
    }

    @Test
    void testSaveAndCreate() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);
        Payment findResult = paymentRepository.getPaymentById(payments.get(1).getId());
        
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getOrder(), findResult.getOrder());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
    }

    @Test
    void testSaveAndCreateVoucher() {
        Payment payment = payments.get(2);
        Payment result = paymentRepository.save(payment);
        Payment findResult = paymentRepository.getPaymentById(payments.get(2).getId());

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getOrder(), findResult.getOrder());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
    }

    @Test
    void testSaveAndCreateBank() {
        Payment payment = payments.get(3);
        Payment result = paymentRepository.save(payment);
        Payment findResult = paymentRepository.getPaymentById(payments.get(3).getId());

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getOrder(), findResult.getOrder());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        assertEquals(PaymentMethod.BANK.getValue(), payment.getMethod());
    }

    @Test
    void testSaveAndCreatePaymentOnDuplicateId() {
        Payment payment = payments.get(1);
        paymentRepository.save(payment);
        assertThrows(IllegalArgumentException.class,
                () -> paymentRepository.save(payment));
    }

    @Test
    void testSaveAndCreateBankPaymentOnDuplicateId() {
        Payment payment = payments.get(3);
        paymentRepository.save(payment);
        assertThrows(IllegalArgumentException.class,
                () -> paymentRepository.save(payment));
    }

    @Test
    void testSaveAndCreateVoucherPaymentOnDuplicateId() {
        Payment payment = payments.get(2);
        paymentRepository.save(payment);
        assertThrows(IllegalArgumentException.class,
                () -> paymentRepository.save(payment));
    }

    @Test
    void testFindByIdIfIdIsFound() {
        for (Payment payment : payments)
            paymentRepository.save(payment);

        Payment findResult = paymentRepository.getPaymentById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertSame(payments.get(1).getPaymentData(), findResult.getPaymentData());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
        assertEquals(payments.get(1).getOrder(), findResult.getOrder());
    }

    @Test
    void testFindByIdIfIdIsNotFound() {
        assertNull(paymentRepository.getPaymentById("zczc"));
    }

    @Test
    void testGetAllPayments() {
        for (Payment payment : payments)
            paymentRepository.save(payment);
        List<Payment> allPayments = paymentRepository.getAllPayments();
        assertEquals(4, allPayments.size());
    }
}