package com.example.demo.UserData;

import java.util.ArrayList;

public class CustomerData {
    private double balance;
    private ArrayList<String> preferredStrategies;


    public CustomerData() {
    }
      public CustomerData(String username, String password, double balance, ArrayList<String> preferredStrategies){
        this.balance = balance;
        this.preferredStrategies = preferredStrategies;
      }
      public double getBalance() {
        return balance;
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
