package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.UserData.CustomerData;
import com.example.demo.UserData.LoginData;

public class Customer {
  private CustomerData customerData;
  private LoginData loginData;
  List<Order> orders;

  public Customer(CustomerData customerData, LoginData loginData) {
    orders = new ArrayList<>();
    this.customerData = customerData;
    this.loginData = loginData;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public CustomerData getCustomerData() {
    return customerData;
  }
  public LoginData getLoginData() {
    return loginData;
  }
  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }

  public void placeOrder(Order order) {
    orders.add(order);
  }

}
