package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Decrator.EmailDecrator;
import com.example.demo.Decrator.INotifier;
import com.example.demo.Decrator.Notifier;
import com.example.demo.Decrator.SMSDecrator;
import com.example.demo.UserData.CustomerData;
import com.example.demo.UserData.LoginData;

public class Customer {
  private CustomerData customerData;
  private LoginData loginData;
  private INotifier iNotifier;
  List<Order> orders;

  public Customer(CustomerData customerData, LoginData loginData) {
    orders = new ArrayList<>();
    this.customerData = customerData;
    this.loginData = loginData;
    setInotifier();
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
  private void setInotifier(){
    iNotifier = new Notifier();
    for(String strategy : customerData.getPreferredStrategy()){
      if(strategy.equals("SMS")){
        iNotifier = new SMSDecrator(iNotifier);
      }
      if(strategy.equals("Email")){
        iNotifier = new EmailDecrator(iNotifier);
      }
    }
  }
  public INotifier getNotifier(){
    return iNotifier;
  }
}
