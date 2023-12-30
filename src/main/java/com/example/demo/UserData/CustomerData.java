package com.example.demo.UserData;

public class CustomerData {
    private double balance;
    private String preferredStrategy; // TPU

    public CustomerData() {
    }
      public CustomerData(String username, String password, double balance, String preferredStrategy){
        this.balance = balance;
        this.preferredStrategy = preferredStrategy;
      }
      public double getBalance() {
        return balance;
      }
      public String getPreferredStrategy() {
        return preferredStrategy;
      }
      public void setBalance(double balance) {
        this.balance = balance;
      }
      public void setPreferredStrategy(String preferredStrategy) {
        this.preferredStrategy = preferredStrategy;
      }
}
