package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Customer;
import com.example.demo.SystemService.CustomerService;
import com.example.demo.UserData.CustomerData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class SystemController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/createAccount")
public ResponseEntity<String> signUp(@RequestBody CustomerData customerData) {
    try {
        Customer customer = new Customer(customerData.getUsername(), customerData.getBalance(), customerData.getPreferredStrategy());
        customerService.addCustomer(customer);
        return ResponseEntity.ok("Account created successfully!");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating account");
    }
}

@PostMapping("/checkUser")
public ResponseEntity<Boolean> isUserExist(@RequestParam String username) {
    try {
        boolean userExists = customerService.isUserExist(username);
        return ResponseEntity.ok(userExists);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
    }
}
    
}
