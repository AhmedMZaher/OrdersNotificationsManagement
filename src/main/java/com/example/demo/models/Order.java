package com.example.demo.models;

import java.util.HashMap;
import java.util.Map;

public abstract class Order {
  private static int orderID = 0;
  protected Customer customer;
  protected HashMap<Product, Integer> products;
  private String shippingAddress;
  protected float totalAmount;
  private OrderStatus orderStatus;

  public Order(){
    orderID++;
    this.products = new HashMap<>();
  }
  // public Order(int orderID, Customer customer, String shippingAddress, HashMap<Integer, Integer> selectedProducts) {
  //   //TODO: Order ID is generated automatically
  //   this.orderID = orderID;
  //   this.customer = customer;
  //   this.shippingAddress = shippingAddress;
  //   this.orderStatus = OrderStatus.PLACED;
  //   this.products = selectedProducts;
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

  public abstract void removeItem(Product product);

  public abstract void showDetails();

  public abstract void shipOrder();

  public abstract float calcPrice();

  public abstract void cancelOrder();

  public abstract void notifyCustomer();
  public abstract void addOrder(Order order);
  public void checkout(){
    calcPrice();
    customer.getCustomerData().setBalance(customer.getCustomerData().getBalance() - totalAmount);
  }
}

