package com.example.demo.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompoundOrder extends Order {

  private List<Order> orders;
  
  // compound  = products + allProductsQuantity 

  public CompoundOrder() {
    super();
    this.orders = new ArrayList<>();
  }

  @Override
  public void addItem(Product product, int quantity) {
    products.put(product,quantity);
  }

  @Override
  public ArrayList<String> showDetails() {
    ArrayList<String> allOrders = new ArrayList<>();
    for(HashMap.Entry<Product, Integer> entry : products.entrySet()){
      allOrders.add(entry.getKey().toString());
    }

    for(Order order : orders){
        ArrayList<String> temp = order.showDetails();
        allOrders.addAll(temp);
    }
    return allOrders;
  }
    
  @Override
  public void shipOrder(double shippingFees) {
    setOrderStatus(OrderStatus.SHIPPED);
    for(Order order : orders)
      order.shipOrder(shippingFees);
  }
  public void addOrder(Order order) {
    orders.add(order);
  }



  @Override
  public boolean checkout() {
      return super.checkout() && orders.stream().allMatch(Order::checkout);
  }
  @Override
  public HashMap<Product, Integer> getAllProductsQuantity(){
    HashMap<Product, Integer> allProductsQuantity = products;
    for(Order order : orders){
      HashMap<Product, Integer> temp = order.getAllProductsQuantity();

      for (Map.Entry<Product, Integer> entry : temp.entrySet()) {
        Product product = entry.getKey();
        int quantity = entry.getValue();
        // Check if the product is already in allProductsQuantity
        if (allProductsQuantity.containsKey(product)) {
            // If yes, add the quantities
            allProductsQuantity.put(product, allProductsQuantity.get(product) + quantity);
        } else {
            // If not, add the product to allProductsQuantity
            allProductsQuantity.put(product, quantity);
        }
    }
    }
    return allProductsQuantity; 
  }
}