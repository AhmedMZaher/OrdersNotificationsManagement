package com.example.demo.models;

import java.util.HashMap;

public abstract class Order {
  private int orderID;
  private Customer customer;
  private HashMap<Product, Integer> products;
  private String shippingAddress;
  private double totalAmount;
  private OrderStatus orderStatus;

  public Order(int orderID, Customer customer, HashMap<Product, Integer> products, String shippingAddress,
      double totalAmount, OrderStatus orderStatus) {
    this.orderID = orderID;
    this.customer = customer;
    this.products = products;
    this.shippingAddress = shippingAddress;
    this.totalAmount = totalAmount;
    this.orderStatus = orderStatus;
  }
  public Order() {
    // TODO Auto-generated constructor stub
  }

  public int getOrderID() {
    return orderID;
  }

  public Customer getCustomer() {
    return customer;
  }

  public HashMap<Product, Integer> getProducts() {
    return products;
  }

  public String getShippingAddress() {
    return shippingAddress;
  }

  public double getTotalAmount() {
    return totalAmount;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public void setOrderID(int orderID) {
    this.orderID = orderID;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public void setProducts(HashMap<Product, Integer> products) {
    this.products = products;
  }

  public void setShippingAddress(String shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public void setTotalAmount(double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }

  public abstract void addItem(Product product, int quantity);

  public abstract void removeItem(Product product);

  public abstract void showDetails();

  public abstract void shipOrder();

  public abstract float calcPrice();

  public abstract void cancelOrder();

  public abstract void notifyCustomer();
}

