package com.example.demo.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class Order {
  private int orderID;
  protected Customer customer;
  protected HashMap<Product, Integer> products;
  private String shippingAddress;
  protected float totalAmount;
  private OrderStatus orderStatus;
  private LocalDateTime orderPlacementTime;

  public Order() {
    totalAmount = 0;
    Random random = new Random();
    this.orderID = random.nextInt(1000000); // TODO generate unique random id
    this.orderStatus = OrderStatus.ONHOLD;
    this.products = new HashMap<>();
    this.orderPlacementTime = LocalDateTime.now();
  }
  // public Order(int orderID, Customer customer, String shippingAddress,
  // HashMap<Integer, Integer> selectedProducts) {
  // //TODO: Order ID is generated automatically
  // this.orderID = orderID;
  // this.customer = customer;
  // this.shippingAddress = shippingAddress;
  // this.orderStatus = OrderStatus.PLACED;
  // this.products = selectedProducts;
  // }

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

  public LocalDateTime getOrderPlacementTime() {
    return orderPlacementTime;
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

  public void setTotalAmount(float totalAmount) {
    this.totalAmount = totalAmount;
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }

  public abstract void addItem(Product product, int quantity);

  public abstract ArrayList<String> showDetails();

  public abstract void shipOrder(double shippingFees);

  public void calcPrice() {
    for (Map.Entry<Product, Integer> item : products.entrySet()) {
      double productPrice = item.getKey().getPrice();
      totalAmount += (productPrice * item.getValue());
    }
  }

  public boolean checkout() {

    if (customer.getCustomerData().getBalance() < totalAmount)
      return false;

    customer.getCustomerData().setBalance(customer.getCustomerData().getBalance() - totalAmount);
    return true;
  }

  public abstract HashMap<Product, Integer> getAllProductsQuantity();
}
