// package com.example.demo.controller;

// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.models.Customer;

// import java.util.ArrayList;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Repository;
// import org.springframework.web.bind.annotation.GetMapping;


// @RestController
// @RequestMapping("/customers")
// public class CustomerController {
//   @Autowired
  

//   @PostMapping
//   public Customer createAccount(@RequestBody Customer customer) {
//       // Implement logic to create a customer account
//       return null;
//   }

//   @PutMapping("/{customerId}/balance")
//   public void setCustomerBalance(@PathVariable Long customerId, @RequestParam double balance) {
//     // Implement logic to set customer balance
//   }
  
//   @GetMapping("/{customerId}/balance")
//   public void getCustomerBalance() {
//     // Implement logic to get customer balance
//   }

//   @GetMapping("/{customerId}/orders")
//   public void getCustomerOrders() {
//     // Implement logic to get customer orders
//   }

//   @GetMapping("/{customerId}/orders/{orderId}")
//   public void getCustomerOrder() {
//     // Implement logic to get customer order
//   }
  
// }
