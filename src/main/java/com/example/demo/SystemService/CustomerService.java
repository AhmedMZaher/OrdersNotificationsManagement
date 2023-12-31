package com.example.demo.SystemService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.CustomersRepo;
import com.example.demo.Repository.ProductsRepo;
import com.example.demo.models.Customer;
import com.example.demo.models.Order;
import com.example.demo.models.OrderStatus;
import com.example.demo.models.Product;

@Service
public class CustomerService {
  @Autowired
  private CustomersRepo customersRepo;
  @Autowired
  private ProductsRepo productsRepo;
  @Autowired
  OrderService orderService;

  public boolean isUserValid(String username, String password) {
    for (Customer customer : customersRepo.getCustomersList()) {
      if (customer.getLoginData().getUsername().equals(username)) {
        if (customer.getLoginData().getPassword().equals(password)) {
          return true;
        }
      }
    }
    return false;
  }

  public double checkOut(String customerUserName, int orderID) {
    Order order = orderService.findOrderByUsername(customerUserName, orderID);

    if (order == null || order.getOrderStatus() != OrderStatus.ONHOLD)
      return -1;

    HashMap<Product, Integer> hashMap = order.getAllProductsQuantity();
    decreaseQuantity(hashMap);
    order.setOrderStatus(OrderStatus.PLACED);
    order.checkout();

    return order.getTotalAmount();
  }

  private void decreaseQuantity(HashMap<Product, Integer> hashMap) {
    for (HashMap.Entry<Product, Integer> entry : hashMap.entrySet()) {
      for (Product product : productsRepo.getProductsList()) {
        if (product.getSerialNumber().equals(entry.getKey().getSerialNumber())) {
          product.setRemainingQuantity(product.getRemainingQuantity() - entry.getValue());
        }
      }
    }
  }

  private void increaseQuantity(HashMap<Product, Integer> hashMap) {
    for (HashMap.Entry<Product, Integer> entry : hashMap.entrySet()) {
      for (Product product : productsRepo.getProductsList()) {
        if (product.getSerialNumber().equals(entry.getKey().getSerialNumber())) {
          product.setRemainingQuantity(product.getRemainingQuantity() + entry.getValue());
        }
      }
    }
  }

  public boolean cancelOrder(String username, int orderID) {
    Order order = orderService.findOrderByUsername(username, orderID);
    if (order == null || order.getOrderStatus() != OrderStatus.PLACED)
      return false;
    double CustomerBalance = order.getCustomer().getCustomerData().getBalance();
    double orderPrice = order.getTotalAmount();
    order.getCustomer().getCustomerData().setBalance(CustomerBalance + orderPrice);
    HashMap<Product, Integer> hashMap = order.getAllProductsQuantity();
    order.setOrderStatus(OrderStatus.CANCELLED);
    increaseQuantity(hashMap);
    return true;
  }

  public boolean cancelShipment(String username, int orderID) {
    Order order = orderService.findOrderByUsername(username, orderID);
    if (order == null || order.getOrderStatus() != OrderStatus.SHIPPED) {
      return false;
    }
    // Check if the order was placed less than 1 minute ago
    LocalDateTime orderPlacementTime = order.getOrderPlacementTime();
    LocalDateTime currentTime = LocalDateTime.now();
    Duration duration = Duration.between(orderPlacementTime, currentTime);
    long minutesElapsed = duration.toMinutes();
    if (minutesElapsed >= 1) {
      return false; // Order cannot be canceled if more than 1 minute has passed
    }
    double customerBalance = order.getCustomer().getCustomerData().getBalance();
    order.getCustomer().getCustomerData().setBalance(customerBalance + orderService.shippingFees);
    order.setOrderStatus(OrderStatus.PLACED);
    return true;
  }

  public double getCustomerBalance(String username) {
    for (Customer customer : customersRepo.getCustomersList()) {
      if (customer.getLoginData().getUsername().equals(username)) {
        return customer.getCustomerData().getBalance();
      }
    }
    return -1;
  }
}
