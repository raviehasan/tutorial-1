package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

class PaymentTest {
    private Map<String, String> paymentData;
    private Order order;
    private List<Product> products;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
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

        order = new Order(
                "gb558e9f-1c39-460e-8860-71af6af63bd6", products,
                1708560000L, "Safira Sudrajat");
    }

    void loadVoucherPaymentData() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    void loadBankTransferPaymentData() {
        paymentData.put("bankName", "BANK_SYARIAH_PUSILKOM");
        paymentData.put("referenceCode", "1234567890");
    }

    @Test
    void testCreatePaymentWithBankTransferPaymentDataOnPendingStatus() {
        loadBankTransferPaymentData();
        Payment payment = new Payment(
                "eb558e9f-1c39-460e-8860-71af6af63bd6", "",
                order, paymentData);

        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherPaymentDataOnPendingStatus() {
        loadVoucherPaymentData();
        Payment payment = new Payment(
                "eb558e9f-1c39-460e-8860-71af6af63bd6", "",
                order, paymentData);
    
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithBankTransferPaymentDataOnSuccessStatus() {
        loadBankTransferPaymentData();
        Payment payment = new Payment(
                "eb558e9f-1c39-460e-8860-71af6af63bd6", "", order,
                paymentData, PaymentStatus.SUCCESS.getValue());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherPaymentDataOnSuccessStatus() {
        loadVoucherPaymentData();
        Payment payment = new Payment(
                "eb558e9f-1c39-460e-8860-71af6af63bd6", "", order,
                paymentData, PaymentStatus.SUCCESS.getValue());
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithBankTransferPaymentDataOnRejectedStatus() {
        loadBankTransferPaymentData();
        Payment payment = new Payment(
                "eb558e9f-1c39-460e-8860-71af6af63bd6", "",
                order, paymentData, PaymentStatus.REJECTED.getValue());
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherPaymentDataOnRejectedStatus() {
        loadVoucherPaymentData();
        Payment payment = new Payment(
                "eb558e9f-1c39-460e-8860-71af6af63bd6", "",
                order, paymentData, PaymentStatus.REJECTED.getValue());
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithBankTransferPaymentDataOnInvalidStatus() {
        loadBankTransferPaymentData();
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new Payment(
                    "eb558e9f-1c39-460e-8860-71af6af63bd6", "", order,
                    paymentData, "MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherPaymentDataOnInvalidStatus() {
        loadVoucherPaymentData();
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new Payment(
                    "eb558e9f-1c39-460e-8860-71af6af63bd6", "", order,
                    paymentData, "MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithBankTransferPaymentDataOnNullStatus() {
        loadBankTransferPaymentData();
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new Payment(
                    "eb558e9f-1c39-460e-8860-71af6af63bd6", "", order,
                    paymentData, null);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherPaymentDataOnNullStatus() {
        loadVoucherPaymentData();
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new Payment(
                    "eb558e9f-1c39-460e-8860-71af6af63bd6", "", order,
                    paymentData, null);
        });
        paymentData.clear();
    }

    @Test
    void testSetPaymentWithBankTransferPaymentDataStatusToSuccessStatus() {
        loadBankTransferPaymentData();
        Payment payment = new Payment(
                "eb558e9f-1c39-460e-8860-71af6af63bd6", "",
                order, paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetPaymentWithVoucherPaymentDataStatusToSuccessStatus() {
        loadVoucherPaymentData();
        Payment payment = new Payment(
                "eb558e9f-1c39-460e-8860-71af6af63bd6", "",
                order, paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetPaymentWithBankTransferPaymentDataStatusToRejectedStatus() {
        loadBankTransferPaymentData();
        Payment payment = new Payment(
                "eb558e9f-1c39-460e-8860-71af6af63bd6", "",
                order, paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetPaymentWithVoucherPaymentDataStatusToRejectedStatus() {
        loadVoucherPaymentData();
        Payment payment = new Payment(
                "eb558e9f-1c39-460e-8860-71af6af63bd6", "",
                order, paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetPaymentWithBankTransferPaymentDataStatusToInvalidStatus() {
        loadBankTransferPaymentData();
        Payment payment = new Payment(
                "eb558e9f-1c39-460e-8860-71af6af63bd6", "",
                order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testSetPaymentWithVoucherPaymentDataStatusToInvalidStatus() {
        loadVoucherPaymentData();
        Payment payment = new Payment(
                "eb558e9f-1c39-460e-8860-71af6af63bd6", "",
                order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testSetPaymentWithBankTransferPaymentDataStatusToNullStatus() {
        loadBankTransferPaymentData();
        Payment payment = new Payment(
                "eb558e9f-1c39-460e-8860-71af6af63bd6", "",
                order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus(null);
        });
        paymentData.clear();
    }

    @Test
    void testSetPaymentWithVoucherPaymentDataStatusToNullStatus() {
        loadVoucherPaymentData();
        Payment payment = new Payment(
                "eb558e9f-1c39-460e-8860-71af6af63bd6", "",
                order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus(null);
        });
        paymentData.clear();
    }
}