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
  public float calcPrice() {
    for (Map.Entry<Product, Integer> item : products.entrySet()) {
            double productPrice = item.getKey().getPrice();
            totalAmount += (productPrice * item.getValue());
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
  public HashMap<Product, Integer> getAllProductsQuantity(){
    return products;
  }
}
