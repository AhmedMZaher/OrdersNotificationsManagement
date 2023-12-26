package com.example.demo.Factory;

import com.example.demo.models.CompoundOrder;
import com.example.demo.models.Order;
import com.example.demo.models.SimpleOrder;

public class CompoundOrderFactory implements OrderFactory {

  @Override
  public Order createOrder(String orderType) {
    if (orderType.equals("simple")) {
      return new SimpleOrder();
    } else if (orderType.equals("compound")) {
      return new CompoundOrder();
    } else {
      return null;
    }
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'createOrder'");
  }

}