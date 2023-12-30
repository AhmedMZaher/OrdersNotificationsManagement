package com.example.demo.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimpleOrder extends Order {

  public SimpleOrder(){
    super();
  }
  @Override
  public void addItem(Product product, int quantity) {
    // TODO Auto-generated method stub
    products.put(product,quantity);
    
  }

  @Override
  public ArrayList<String> showDetails() {
    ArrayList<String> allOrders = new ArrayList<>();
    for(HashMap.Entry<Product, Integer> entry : products.entrySet()){
      allOrders.add(entry.getKey().toString() + " Order Quantity: " + entry.getValue());
    }
    return allOrders;
  }

  @Override
  public void shipOrder(double shippingFees) {
    setOrderStatus(OrderStatus.SHIPPED);
    customer.getCustomerData().setBalance(customer.getCustomerData().getBalance() - shippingFees);
  }

  @Override
  public HashMap<Product, Integer> getAllProductsQuantity(){
    return products;
  }
}
