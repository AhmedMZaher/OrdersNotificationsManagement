package com.example.demo.models;

import java.util.List;

import com.example.demo.Strategy.Strategy;

public class Customer  {
  private String username;
  private double balance;
  List<Order> orders;
  private Strategy preferredStrategy;
  

  public Customer(String username, double balance, List<Order> orders, Strategy preferredStrategy) {
    this.username = username;
    this.balance = balance;
    this.orders = orders;
    this.preferredStrategy = preferredStrategy;
  }

  public String getUsername() {
    return username;
  }

  public double getBalance() {
    return balance;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public Strategy getPreferredStrategy() {
    return preferredStrategy;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }

  public void setPreferredStrategy(Strategy preferredStrategy) {
    this.preferredStrategy = preferredStrategy;
  }

  public void placeOrder(Order order) {
    orders.add(order);
  }

}
