package com.example.demo.models;

import java.util.List;

public class CompoundOrder extends Order {

  private List<Order> orders;

  public CompoundOrder() {
    // TODO Auto-generated constructor stub
    super();
  }
  
  @Override
  public void addItem(Product product, int quantity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addItem'");
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
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'calcPrice'");
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
  
}
