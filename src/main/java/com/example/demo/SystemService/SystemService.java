package com.example.demo.SystemService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.Factory.OrderFactory;
import com.example.demo.models.CompoundOrder;
import com.example.demo.models.Customer;
import com.example.demo.models.Order;
import com.example.demo.models.OrderStatus;
import com.example.demo.models.Product;

@Service
public class SystemService {
  private List<Product> productsList = new ArrayList<>();
  private List<Customer> customersList = new ArrayList<>();
  private OrderFactory orderFactory = new OrderFactory();

  public void addCustomer(Customer customer) {
    customersList.add(customer);
  }

  public Customer isUserExist(String username) {
      return customersList.stream().filter(customer -> customer.getCustomerData().getUsername().equals(username))
              .findFirst().orElse(null);
  }

  public SystemService() {
    // 1 + 6 + 10 = 17 + 6
    productsList.add(new Product("1", "Apple", "Apple Inc.", "Fruit", 1.0, 100));
    productsList.add(new Product("2", "Banana", "Banana Inc.", "Fruit", 2.0, 100));
    productsList.add(new Product("3", "Orange", "Orange Inc.", "Fruit", 3.0, 100));
    productsList.add(new Product("4", "Mango", "Mango Inc.", "Fruit", 4.0, 100));
    productsList.add(new Product("5", "Pineapple", "Pineapple Inc.", "Fruit", 5.0, 100));
    productsList.add(new Product("6", "Strawberry", "Strawberry Inc.", "Fruit", 6.0, 100));
    productsList.add(new Product("7", "Blueberry", "Blueberry Inc.", "Fruit", 7.0, 100));
    productsList.add(new Product("8", "Raspberry", "Raspberry Inc.", "Fruit", 8.0, 100));
    productsList.add(new Product("9", "Blackberry", "Blackberry Inc.", "Fruit", 9.0, 100));
    productsList.add(new Product("10", "Watermelon", "Watermelon Inc.", "Fruit", 10.0, 100));
    productsList.add(new Product("11", "Grapes", "Grapes Inc.", "Fruit", 11.0, 100));
    productsList.add(new Product("12", "Cherry", "Cherry Inc.", "Fruit", 12.0, 100));
    productsList.add(new Product("13", "Kiwi", "Kiwi Inc.", "Fruit", 13.0, 100));
    productsList.add(new Product("14", "Peach", "Peach Inc.", "Fruit", 14.0, 100));
    productsList.add(new Product("15", "Pear", "Pear Inc.", "Fruit", 15.0, 100));
    productsList.add(new Product("16", "Plum", "Plum Inc.", "Fruit", 16.0, 100));
    productsList.add(new Product("17", "Avocado", "Avocado Inc.", "Fruit", 17.0, 100));
  }
  public boolean isOrderValid(HashMap<String, Integer> selectedProducts) {
    for (HashMap.Entry<String, Integer> entry : selectedProducts.entrySet()) {
      String serialNumber = entry.getKey();
      Integer quantity = entry.getValue();
      Product product = productsList.stream().filter(p -> p.getSerialNumber().equals(serialNumber)).findFirst().orElse(null);
      if (product == null || product.getRemainingQuantity() < quantity) {
        return false;
      }
    }
    return true;
  }

  public Order createOrder(HashMap<String, Integer> selectedProducts, String orderType, String username) {
    boolean isOrderValid = isOrderValid(selectedProducts);
    if (!isOrderValid) {
      return null;
    }
    Customer customer = isUserExist(username);
    if (customer == null) {
      return null;
    }
    Order order = this.orderFactory.createOrder(orderType);

    for (HashMap.Entry<String, Integer> entry : selectedProducts.entrySet()) {
      String serialNumber = entry.getKey();
      Integer quantity = entry.getValue();
      Product product = productsList.stream().filter(p -> p.getSerialNumber().equals(serialNumber)).findFirst()
          .orElse(null);
      if(product == null)
        return null;
      order.addItem(product, quantity);
    }
    order.calcPrice();
    order.setCustomer(customer);
    customer.placeOrder(order);
    return order;
  }

  private Order findOrderByUsername(String username, int orderID){
    Customer customer = null;
    for(Customer ccustomer : customersList){
      if(ccustomer.getCustomerData().getUsername().equals(username)){
       customer = ccustomer;
       break;
      }
    }
    Order order = null;
    List<Order> orders = customer.getOrders();
    for(Order currOrder : orders){
      if(currOrder.getOrderID() == orderID){
          order = currOrder;
          break;
      }
    }
    return order;
  }
  public Order createCompoundOrder(HashMap<String, Integer> selectedProducts, String orderType, String username, HashMap<String, Integer> simpleOrders){
    CompoundOrder compoundOrder = (CompoundOrder)createOrder(selectedProducts, "CompoundOrder", username); // TBD
    Customer customer = isUserExist(username);
   
    compoundOrder.setCustomer(customer);
    customer.placeOrder(compoundOrder);

    for(HashMap.Entry<String, Integer> entery : simpleOrders.entrySet()){
      String currUsername = entery.getKey();
      int currOrderID = entery.getValue();
      Order currOrder = findOrderByUsername(currUsername, currOrderID);
      if(currOrder == null)
        return null;
      compoundOrder.addOrder(currOrder);
    }

    return compoundOrder;
  }


  public double checkOut(String customerUserName, int orderID){
    Order order = findOrderByUsername(customerUserName, orderID);
    
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
      for(Product product : productsList){
        if(product.getSerialNumber().equals(entry.getKey().getSerialNumber())){
          product.setRemainingQuantity(product.getRemainingQuantity() - entry.getValue());
        }
      }
    }
  }

  public double getCustomerBalance(String username){
    for (Customer customer : customersList) {
      if (customer.getCustomerData().getUsername().equals(username)) {
          return customer.getCustomerData().getBalance();
      }
    }
    return -1;
  }
  public int getProductsQuantity(String serialnumber){
    for(Product product : productsList){
        if(product.getSerialNumber().equals(serialnumber)){
          return product.getRemainingQuantity();
        }
      }
      return -1;
  }
}
