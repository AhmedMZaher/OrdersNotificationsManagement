package com.example.demo.controller;


import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Customer;

import jakarta.websocket.server.PathParam;

import com.example.demo.SystemService.SystemService;
import com.example.demo.UserData.CustomerData;
import com.example.demo.UserData.LoginData;
import com.example.demo.UserData.RequestData;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class SystemController {
    @Autowired
    private SystemService systemService;
    @Autowired
    private LoggingController loggingController;

    @PostMapping("/createAccount")
    public ResponseEntity<String> signUp(@RequestBody RequestData requestData) {
        CustomerData customerData = requestData.getCustomerData();
        LoginData loginData = requestData.getLoginData();
        try {
            Customer customer = new Customer(customerData, loginData);
            Customer userExists = systemService.isUserExist(loginData.getUsername());
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
    public ResponseEntity<String> login(@RequestBody LoginData loginData) {
        Boolean isUserValid = systemService.isUserValid(loginData.getUsername(), loginData.getPassword());
        
        if (isUserValid == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or password is not true");
        }
        else{
            loggingController.login(loginData.getUsername(), loginData.getPassword());
            return ResponseEntity.ok("Login successful!");
        }
            
    
    }
}
