package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    PaymentService paymentService;

    @Mock
    InventoryService inventoryService;

    @InjectMocks
    OrderService orderService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testOrderSuccess() {
        when(inventoryService.checkStock("P101")).thenReturn(true);
        when(paymentService.processPayment(500)).thenReturn(true);

        String result = orderService.placeOrder("P101", 500);

        assertEquals("Order Successful", result);
    }

    @Test
    void testOutOfStock() {
        when(inventoryService.checkStock("P101")).thenReturn(false);

        String result = orderService.placeOrder("P101", 500);

        assertEquals("Out of Stock", result);

        verify(paymentService, never()).processPayment(anyDouble());
    }

    @Test
    void testPaymentFailed() {
        when(inventoryService.checkStock("P101")).thenReturn(true);
        when(paymentService.processPayment(500)).thenReturn(false);

        String result = orderService.placeOrder("P101", 500);

        assertEquals("Payment Failed", result);
    }

    @Test
    void testPaymentThrowsException() {
        when(inventoryService.checkStock("P101")).thenReturn(true);
        when(paymentService.processPayment(500)).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () ->
                orderService.placeOrder("P101", 500));
    }

    @Test
    void verifyPaymentCalledOnce() {
        when(inventoryService.checkStock("P101")).thenReturn(true);
        when(paymentService.processPayment(500)).thenReturn(true);

        orderService.placeOrder("P101", 500);

        verify(paymentService, times(1)).processPayment(500);
    }

    @Test
    void testDifferentProductIds() {
        when(inventoryService.checkStock("P200")).thenReturn(true);
        when(paymentService.processPayment(300)).thenReturn(true);

        String result = orderService.placeOrder("P200", 300);

        assertEquals("Order Successful", result);
    }

    @Test
    void testNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () ->
                orderService.placeOrder("P101", -100));
    }
}
