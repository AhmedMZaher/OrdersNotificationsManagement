package com.example.demo.controller;

import java.util.List;

import javax.management.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Customer;
import com.example.demo.models.Product;
import com.example.demo.Repository.CustomersRepo;
import com.example.demo.Repository.ProductsRepo;
import com.example.demo.Singleton.LoggingController;
import com.example.demo.SystemService.NotificationsService;
import com.example.demo.SystemService.OrderService;
import com.example.demo.SystemService.CustomerService;
import com.example.demo.UserData.CustomerData;
import com.example.demo.UserData.LoginData;
import com.example.demo.UserData.RequestData;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class SystemController {
    @Autowired
    private OrderService systemService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private NotificationsService notificationsService;
    @Autowired
    private LoggingController loggingController;
    @Autowired
    private ProductsRepo productsRepo;
    @Autowired
    private CustomersRepo customersRepo;

    @PostMapping("/createAccount")
    public ResponseEntity<String> signUp(@RequestBody RequestData requestData) {
        if (loggingController.isLoggedIn()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please sign out first!");
        }
        CustomerData customerData = requestData.getCustomerData();
        LoginData loginData = requestData.getLoginData();
        try {
            Customer customer = new Customer(customerData, loginData);
            Customer userExists = customersRepo.isUserExist(loginData.getUsername());
            if (userExists != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
            }
            customersRepo.addCustomer(customer);
            return ResponseEntity.ok("Account created successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating account");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginData loginData) {
        if (loggingController.isLoggedIn()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please sign out first!");
        }
        Boolean isUserValid = customerService.isUserValid(loginData.getUsername(), loginData.getPassword());

        if (isUserValid == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or password is not true");
        } else {
            loggingController.login(loginData.getUsername(), loginData.getPassword());
            return ResponseEntity.ok("Login successful!");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        if (!loggingController.isLoggedIn()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please login in!");
        }
        loggingController.logout();
        return ResponseEntity.ok("You have logged out successfully!");
    }

    @GetMapping("/getProductsList")
    public ResponseEntity<List<Product>> getProductsList() {
        List<Product> productsList = productsRepo.getProductsList();
        return ResponseEntity.ok(productsList);
    }
}
