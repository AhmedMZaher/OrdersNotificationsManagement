package com.example.demo.SystemService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.Factory.OrderFactory;
import com.example.demo.models.Customer;
import com.example.demo.models.Order;
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
    productsList.add(new Product(1, "Apple", "Apple Inc.", "Fruit", 1.0, 100));
    productsList.add(new Product(2, "Banana", "Banana Inc.", "Fruit", 2.0, 100));
    productsList.add(new Product(3, "Orange", "Orange Inc.", "Fruit", 3.0, 100));
    productsList.add(new Product(4, "Mango", "Mango Inc.", "Fruit", 4.0, 100));
    productsList.add(new Product(5, "Pineapple", "Pineapple Inc.", "Fruit", 5.0, 100));
    productsList.add(new Product(6, "Strawberry", "Strawberry Inc.", "Fruit", 6.0, 100));
    productsList.add(new Product(7, "Blueberry", "Blueberry Inc.", "Fruit", 7.0, 100));
    productsList.add(new Product(8, "Raspberry", "Raspberry Inc.", "Fruit", 8.0, 100));
    productsList.add(new Product(9, "Blackberry", "Blackberry Inc.", "Fruit", 9.0, 100));
    productsList.add(new Product(10, "Watermelon", "Watermelon Inc.", "Fruit", 10.0, 100));
    productsList.add(new Product(11, "Grapes", "Grapes Inc.", "Fruit", 11.0, 100));
    productsList.add(new Product(12, "Cherry", "Cherry Inc.", "Fruit", 12.0, 100));
    productsList.add(new Product(13, "Kiwi", "Kiwi Inc.", "Fruit", 13.0, 100));
    productsList.add(new Product(14, "Peach", "Peach Inc.", "Fruit", 14.0, 100));
    productsList.add(new Product(15, "Pear", "Pear Inc.", "Fruit", 15.0, 100));
    productsList.add(new Product(16, "Plum", "Plum Inc.", "Fruit", 16.0, 100));
    productsList.add(new Product(17, "Avocado", "Avocado Inc.", "Fruit", 17.0, 100));
  }

  public boolean isOrderValid(Map<Integer, Integer> selectedProducts) {
    for (Map.Entry<Integer, Integer> entry : selectedProducts.entrySet()) {
      Integer serialNumber = entry.getKey();
      Integer quantity = entry.getValue();
      Product product = productsList.stream().filter(p -> p.getSerialNumber() == serialNumber).findFirst().orElse(null);
      if (product == null || product.getRemainingQuantity() < quantity) {
        return false;
      }
    }
    return true;
  }

  public boolean createOrder(Map<Integer, Integer> selectedProducts, String orderType, String username) {
    boolean isOrderValid = isOrderValid(selectedProducts);
    if (!isOrderValid) {
      return false;
    }
    Customer customer = isUserExist(username);
    if (customer == null) {
      return false;
    }
    Order order = this.orderFactory.createOrder(orderType);
    for (Map.Entry<Integer, Integer> entry : selectedProducts.entrySet()) {
      Integer serialNumber = entry.getKey();
      Integer quantity = entry.getValue();
      Product product = productsList.stream().filter(p -> p.getSerialNumber() == serialNumber).findFirst()
          .orElse(null);
      order.addItem(product, quantity);
    }
    float totalAmount = order.calcPrice();
    if (customer.getCustomerData().getBalance() < totalAmount) {
      return false;
    }
    customer.getCustomerData().setBalance(customer.getCustomerData().getBalance() - totalAmount);
    customer.placeOrder(order);
    return true;
  }
}
