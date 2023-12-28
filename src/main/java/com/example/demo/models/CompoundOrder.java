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
  public void removeItem(Product product) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'removeItem'");
  }

  @Override
  public void showDetails() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'showDetails'");
  }

  @Override
  public void shipOrder() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'shipOrder'");
  }
  public void addOrder(Order order) {
    orders.add(order);
  }

  @Override
  public float calcPrice() {
      for (Map.Entry<Product, Integer> item : products.entrySet()) {
              double productPrice = item.getKey().getPrice();
              totalAmount += (productPrice * item.getValue());
      }
      for (Order order : orders) {
        totalAmount += order.calcPrice();
      }
      return totalAmount;
  }

  @Override
  public void cancelOrder() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'cancelOrder'");
  }

  @Override
  public void notifyCustomer() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'notifyCustomer'");
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
