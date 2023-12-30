package com.example.demo.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.ProductsRepo;
import com.example.demo.SystemService.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductsRepo productsRepo;

    @GetMapping("/getProductQuantity")
    public ResponseEntity<Object> getMethodName(@RequestParam String serialnumber) {
        int quantity = productsRepo.getProductsQuantity(serialnumber);
        if(quantity == -1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product is not exist");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(quantity);
        }
    }
    
}
