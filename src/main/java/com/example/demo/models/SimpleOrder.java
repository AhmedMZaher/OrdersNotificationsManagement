package com.example.demo.models;

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
  public HashMap<Product, Integer> getAllProductsQuantity(){
    return products;
  }
}
