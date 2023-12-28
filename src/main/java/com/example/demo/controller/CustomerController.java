package com.example.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.SystemService.SystemService;
import com.example.demo.models.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/customers")
public class CustomerController {
  @Autowired
  private SystemService systemService;
  
  @PostMapping("/createSimpleOrder")
  public ResponseEntity<Object> createOrder(@RequestBody HashMap<String, Integer> selectedProducts, @RequestParam String username) {
    Order order = systemService.createOrder(selectedProducts, "SimpleOrder", username);
    if(order == null){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order was not created");
    }
    int orderID = order.getOrderID();
    return ResponseEntity.ok("Order created successfully! with id: " + orderID);
  }
  @PostMapping("/createCompoundOrder")
 public ResponseEntity<Object> createCompoundOrder(
    @RequestParam String username,
    @RequestBody Map<String, Object> requestBody) {
    HashMap<String, Integer> selectedProducts = (HashMap<String, Integer>) requestBody.get("selectedProducts");
    HashMap<String, Integer> simpleOrders = (HashMap<String, Integer>) requestBody.get("simpleOrders");

    Order order = systemService.createCompoundOrder(selectedProducts, "CompoundOrder", username, simpleOrders);
    if(order == null){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order was not created");
    }
    int orderID = order.getOrderID();
    return ResponseEntity.ok("Order created successfully! with id: " + order.getOrderID());
  }
  @PostMapping("/checkout")
  public ResponseEntity<Object> postMethodName(@RequestParam String username, @RequestParam int orderID) {
      double isOrderChecked = systemService.checkOut(username, orderID);
      if(isOrderChecked == -1)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error please try again !");
      return ResponseEntity.ok("Order placed, Thank you for using our service with amount: " + isOrderChecked);
  }
  
  @GetMapping("/getBalance")
  public ResponseEntity<Object> getCustomerBalance(@RequestParam String username) {
    double balance = systemService.getCustomerBalance(username);
    if(balance == -1)
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not found");
     
    return ResponseEntity.ok(balance);
  }

  @GetMapping("cancelOrder")
  public ResponseEntity<Object> cancelOrder(@RequestParam String username, @RequestParam int orderID) {
      boolean isOrderCanceled = systemService.cancelOrder(username, orderID);
      if(isOrderCanceled == false)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error please try again !");
      return ResponseEntity.ok("Order is canceled successfully!");
  }
  @GetMapping("getOrder")
  public ResponseEntity<Object> getOrder(@RequestParam String username, @RequestParam int orderID) {
      ArrayList<String> orderDetails = systemService.getOrder(username, orderID);
      if(orderDetails.isEmpty())
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error please try again! ");
      return ResponseEntity.ok(orderDetails);
  }
  @GetMapping("/shipOrder")
  public ResponseEntity<Object> shipOrder(@RequestParam String username, @RequestParam int orderID) {
      boolean isShipped = systemService.shipOrder(username, orderID);
      if(!isShipped){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error please try again! ");
      }
      return ResponseEntity.ok("Order is shipped!");
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