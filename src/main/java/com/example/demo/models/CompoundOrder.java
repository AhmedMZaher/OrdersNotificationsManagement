package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompoundOrder extends Order {

  private List<Order> orders;

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
  @Override
  public void addOrder(Order order) {
    orders.add(order);
  }

  @Override
  public float calcPrice() {
      for (Map.Entry<Product, Integer> item : products.entrySet()) {
              Integer value = item.getValue();
              totalAmount += value;
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
  public void checkout() {
    super.checkout();
    for(Order order : orders){
      order.checkout();
    }
  }
  
}
