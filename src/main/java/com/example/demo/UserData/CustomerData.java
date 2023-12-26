package com.example.demo.UserData;

public class CustomerData {
    private String username;
    private double balance;
    private String preferredStrategy; // TPU

    public CustomerData( String username, double balance, String preferredStrategy){
      this.username = username;
      this.balance = balance;
      this.preferredStrategy = preferredStrategy;
    }
    public String getUsername() {
        return username;
      }
    
      public double getBalance() {
        return balance;
      }
      public String getPreferredStrategy() {
        return preferredStrategy;
      }
    
      public void setUsername(String username) {
        this.username = username;
      }
    
      public void setBalance(double balance) {
        this.balance = balance;
      }
      public void setPreferredStrategy(String preferredStrategy) {
        this.preferredStrategy = preferredStrategy;
      }
}
