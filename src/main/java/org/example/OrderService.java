package org.example;

public class OrderService {

    private PaymentService paymentService;
    private InventoryService inventoryService;

    public OrderService(PaymentService paymentService, InventoryService inventoryService) {
        this.paymentService = paymentService;
        this.inventoryService = inventoryService;
    }

    public String placeOrder(String productId, double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }

        boolean inStock = inventoryService.checkStock(productId);

        if (!inStock) {
            return "Out of Stock";
        }

        boolean paymentSuccess = paymentService.processPayment(amount);

        if (!paymentSuccess) {
            return "Payment Failed";
        }

        return "Order Successful";
    }
}
