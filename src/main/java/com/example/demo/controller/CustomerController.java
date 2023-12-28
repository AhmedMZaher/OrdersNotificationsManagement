package com.example.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.SystemService.SystemService;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/customers")
public class CustomerController {
  @Autowired
  private SystemService systemService;
  
  @PostMapping("/createOrder")
  public ResponseEntity<String> createOrder(@RequestBody HashMap<Integer, Integer> selectedProducts, @RequestParam String orderType, @RequestParam String username) {
    boolean isOrderCreated = systemService.createOrder(selectedProducts, orderType, username);
    if (!isOrderCreated) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order was not created");
    }
    return ResponseEntity.ok("Order created successfully!");
  }
  

  @PutMapping("/{customerId}/balance")
  public void setCustomerBalance(@PathVariable Long customerId, @RequestParam double balance) {
    // Implement logic to set customer balance
  }
  
  @GetMapping("/{customerId}/balance")
  public void getCustomerBalance() {
    // Implement logic to get customer balance
  }

  @GetMapping("/{customerId}/orders")
  public void getCustomerOrders() {
    // Implement logic to get customer orders
  }

  @GetMapping("/{customerId}/orders/{orderId}")
  public void getCustomerOrder() {
    // Implement logic to get customer order
  }
  
}
