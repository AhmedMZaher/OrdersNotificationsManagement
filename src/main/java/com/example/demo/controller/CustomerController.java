package com.example.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Singleton.LoggingController;
import com.example.demo.SystemService.OrderService;
import com.example.demo.SystemService.UserService;
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
  private OrderService orderService;
  @Autowired
  private LoggingController loggingController;
  @Autowired
  private UserService userService;

  @PostMapping("/createSimpleOrder")
  public ResponseEntity<Object> createOrder(@RequestBody HashMap<String, Integer> selectedProducts) {
    if(!loggingController.isLoggedIn()){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please login in");
    }
    Order order = orderService.createOrder(selectedProducts, "SimpleOrder", loggingController.getUsername());
    if(order == null){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order was not created");
    }
    int orderID = order.getOrderID();
    return ResponseEntity.ok("Order created successfully! with id: " + orderID);
  }
  @PostMapping("/createCompoundOrder")
 public ResponseEntity<Object> createCompoundOrder(
    @RequestBody Map<String, Object> requestBody) {
    HashMap<String, Integer> selectedProducts = (HashMap<String, Integer>) requestBody.get("selectedProducts");
    HashMap<String, Integer> simpleOrders = (HashMap<String, Integer>) requestBody.get("simpleOrders");
    if(!loggingController.isLoggedIn()){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please login in");
    }

    Order order = orderService.createCompoundOrder(selectedProducts, "CompoundOrder", loggingController.getUsername(), simpleOrders);
    if(order == null){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order was not created");
    }
    int orderID = order.getOrderID();
    return ResponseEntity.ok("Order created successfully! with id: " + order.getOrderID());
  }
  @PostMapping("/checkout")
  public ResponseEntity<Object> postMethodName(@RequestParam int orderID) {
      if(!loggingController.isLoggedIn()){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please login in");
      }
      double isOrderChecked = userService.checkOut(loggingController.getUsername(), orderID);
      if(isOrderChecked == -1)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error please try again !");
      return ResponseEntity.ok("Order placed, Thank you for using our service with amount: " + isOrderChecked);
  }
  
  @GetMapping("/getBalance")
  public ResponseEntity<Object> getCustomerBalance() {
    if(!loggingController.isLoggedIn()){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please login in");
    }
    double balance = userService.getCustomerBalance(loggingController.getUsername());
    if(balance == -1)
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not found");
     
    return ResponseEntity.ok(balance);
  }

  @GetMapping("cancelOrder")
  public ResponseEntity<Object> cancelOrder(@RequestParam int orderID) {
      if(!loggingController.isLoggedIn()){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please login in");
      }
      boolean isOrderCanceled = userService.cancelOrder(loggingController.getUsername(), orderID);
      if(isOrderCanceled == false)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error please try again !");
      return ResponseEntity.ok("Order is canceled successfully!");
  }
  @GetMapping("getOrder")
  public ResponseEntity<Object> getOrder(@RequestParam int orderID) {
      if(!loggingController.isLoggedIn()){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please login in");
      }
      ArrayList<String> orderDetails = orderService.getOrder(loggingController.getUsername(), orderID);
      if(orderDetails.isEmpty())
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error please try again! ");
      return ResponseEntity.ok(orderDetails);
  }
  @GetMapping("/shipOrder")
  public ResponseEntity<Object> shipOrder(@RequestParam int orderID) {
      if(!loggingController.isLoggedIn()){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please login in");
      }
      boolean isShipped = orderService.shipOrder(loggingController.getUsername(), orderID);
      if(!isShipped){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error please try again! ");
      }
      return ResponseEntity.ok("Order is shipped!");
  }

}