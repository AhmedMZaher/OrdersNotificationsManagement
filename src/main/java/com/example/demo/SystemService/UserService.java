package com.example.demo.SystemService;

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
public class UserService {
    @Autowired
    private CustomersRepo customersRepo;
    @Autowired
    private ProductsRepo productsRepo;
    @Autowired 
    OrderService systemService;

    

    public boolean isUserValid(String username, String password){
    for(Customer customer : customersRepo.getCustomersList()){
      if(customer.getLoginData().getUsername().equals(username)){
        if(customer.getLoginData().getPassword().equals(password)){
          return true;
        }
      }
    }
    return false;
  }
  public double checkOut(String customerUserName, int orderID){
    Order order = systemService.findOrderByUsername(customerUserName, orderID);
    
    if(order == null || order.getOrderStatus() != OrderStatus.ONHOLD)
      return -1;

    HashMap<Product, Integer> hashMap = order.getAllProductsQuantity();
    decreaseQuantity(hashMap);
    order.setOrderStatus(OrderStatus.PLACED);
    order.checkout();
    
    return order.getTotalAmount();
  }

  private void decreaseQuantity(HashMap<Product, Integer> hashMap){
    for(HashMap.Entry<Product, Integer> entry : hashMap.entrySet()){
      for(Product product : productsRepo.getProductsList()){
        if(product.getSerialNumber().equals(entry.getKey().getSerialNumber())){
          product.setRemainingQuantity(product.getRemainingQuantity() - entry.getValue());
        }
      }
    }
  }

  private void increaseQuantity(HashMap<Product, Integer> hashMap){
    for(HashMap.Entry<Product, Integer> entry : hashMap.entrySet()){
      for(Product product : productsRepo.getProductsList()){
        if(product.getSerialNumber().equals(entry.getKey().getSerialNumber())){
          product.setRemainingQuantity(product.getRemainingQuantity() + entry.getValue());
        }
      }
    }
  }
  public boolean cancelOrder(String username, int orderID){
    Order order = systemService.findOrderByUsername(username, orderID);
    if(order == null || order.getOrderStatus() != OrderStatus.PLACED)
      return false;
    double CustomerBalance = order.getCustomer().getCustomerData().getBalance();
    double orderPrice = order.getTotalAmount();
    order.getCustomer().getCustomerData().setBalance(CustomerBalance + orderPrice);
    HashMap<Product, Integer> hashMap = order.getAllProductsQuantity();
    order.setOrderStatus(OrderStatus.CANCELLED);
    increaseQuantity(hashMap);
    return true;
  }
  public double getCustomerBalance(String username){
    for (Customer customer : customersRepo.getCustomersList()) {
      if (customer.getLoginData().getUsername().equals(username)) {
          return customer.getCustomerData().getBalance();
      }
    }
    return -1;
  }
}
