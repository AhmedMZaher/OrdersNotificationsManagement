package com.example.demo.models;

import java.util.Arrays;


public class PlacementTemplate extends NotificationTemplate {
    public PlacementTemplate(Order order){
        super(
                "OrderPlacement",
                "Order Placement Confirmation",
                "Dear " + order.getCustomer().getLoginData().getUsername() + ", your order for " + order.getOrderID() + " is Shipped.",
                Arrays.asList("en", "fr"),
                order.getCustomer());
    }
}