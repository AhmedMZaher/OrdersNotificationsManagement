package com.example.demo.models;

import java.util.Arrays;

public class ShipmentTemplate extends NotificationTemplate {
    @Override
    public NotificationTemplate createTemplate(String username, Order order) {
        return new NotificationTemplate(
                "OrderShipped",
                "Order Shipment Confirmation",
                "Dear " + username + ", your order for " + order.getOrderID() + " is Shipped.",
                Arrays.asList("en", "fr"),
                Arrays.asList(username, String.valueOf(order.getOrderID()))
        );
    }
}