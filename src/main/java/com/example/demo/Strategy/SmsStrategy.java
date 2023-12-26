package com.example.demo.Strategy;

public class SmsStrategy implements Strategy {
  public void send() {
    System.out.println("Sending SMS...");
  }
}
