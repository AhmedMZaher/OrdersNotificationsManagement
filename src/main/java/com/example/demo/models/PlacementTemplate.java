package com.example.demo.models;

import java.util.Arrays;


public class PlacementTemplate extends NotificationTemplate {
    @Override
    public NotificationTemplate createTemplate(String username, Order order) {
        return new NotificationTemplate(
                "OrderPlacement",
                "Order Placement Confirmation",
                "Dear " + username + ", your order for " + order.getOrderID() + " is confirmed.",
                Arrays.asList("en", "fr"),
                Arrays.asList(username, String.valueOf(order.getOrderID()))
        );
    }
}