package com.example.demo.Factory;

import com.example.demo.models.CompoundOrder;
import com.example.demo.models.Order;
import com.example.demo.models.SimpleOrder;

public class OrderFactory {
  public Order createOrder(String orderType) {
    if (orderType.equals("SimpleOrder")) {
      return new SimpleOrder();
    } else if (orderType.equals("CompoundOrder")) {
      return new CompoundOrder();
    } else {
      return null;
    }
  }
} 
