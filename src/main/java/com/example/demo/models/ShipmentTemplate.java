package com.example.demo.models;

import java.util.Arrays;

public class ShipmentTemplate extends NotificationTemplate {
    public ShipmentTemplate(Order order){
        super(
                "OrderShipped",
                "Order Shipment Confirmation",
                "Dear " + order.getCustomer().getLoginData().getUsername() + ", your order for " + order.getOrderID() + " is Shipped.",
                Arrays.asList("en", "fr"),
                order.getCustomer());
    }
}