package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.UserData.CustomerData;

public class Customer {
  private CustomerData customerData;
  List<Order> orders;

  public Customer(CustomerData customerData) {
    orders = new ArrayList<>();
    this.customerData = customerData;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public CustomerData getCustomerData() {
    return customerData;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }

  public void placeOrder(Order order) {
    orders.add(order);
  }

}
