package com.example.demo.UserData;

import java.util.ArrayList;

public class CustomerData {
    private double balance;
    private ArrayList<String> preferredStrategies;
    private String email;
    private String phoneNumber;


    public CustomerData() {
    }
      public CustomerData(String password, double balance, ArrayList<String> preferredStrategies, String email, String phoneNumber){
        this.balance = balance;
        this.preferredStrategies = preferredStrategies;
        this.email = email;
        this.phoneNumber = phoneNumber;
      }
      public double getBalance() {
        return balance;
      }
      public String getEmail() {
        return email;
      }
      public String getPhoneNumber() {
        return phoneNumber;
      }
      public  ArrayList<String> getPreferredStrategy() {
        return preferredStrategies;
      }
      public void setBalance(double balance) {
        this.balance = balance;
      }
      public void setPreferredStrategy(ArrayList<String> preferredStrategies) {
        this.preferredStrategies = preferredStrategies;
      }
}
