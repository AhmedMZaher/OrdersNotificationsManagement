package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Customer;
import com.example.demo.SystemService.SystemService;
import com.example.demo.UserData.CustomerData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class SystemController {
    @Autowired
    private SystemService systemService;

    @PostMapping("/createAccount")
    public ResponseEntity<String> signUp(@RequestBody CustomerData customerData) {
        try {
            Customer customer = new Customer(customerData);
            Customer userExists = systemService.isUserExist(customerData.getUsername());
            if (userExists != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
            }
            systemService.addCustomer(customer);
            return ResponseEntity.ok("Account created successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating account");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username) {
        try {
            Customer userExists = systemService.isUserExist(username);
            if (userExists == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist");
            }
            return ResponseEntity.ok("Login successful!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error logging in");
        }
    }

    
    
    
}
