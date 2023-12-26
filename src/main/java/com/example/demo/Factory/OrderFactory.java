package com.example.demo.Factory;

import com.example.demo.models.Order;

public interface OrderFactory {
  public Order createOrder(String orderType);
} 
