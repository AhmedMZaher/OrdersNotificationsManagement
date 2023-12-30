package com.example.demo.SystemService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.Factory.OrderFactory;
import com.example.demo.Repository.CustomersRepo;
import com.example.demo.Repository.NotificationQueueService;
import com.example.demo.Repository.ProductsRepo;
import com.example.demo.models.CompoundOrder;
import com.example.demo.models.Customer;
import com.example.demo.models.NotificationTemplate;
import com.example.demo.models.Order;
import com.example.demo.models.OrderStatus;
import com.example.demo.models.PlacementTemplate;
import com.example.demo.models.Product;
import com.example.demo.models.ShipmentTemplate;

@Service
@EnableScheduling
@Component
public class OrderService {

  @Autowired
  private CustomersRepo customersRepo;
  @Autowired
  private ProductsRepo productsRepo;
  @Autowired
  NotificationQueueService notificationQueueService;
  private OrderFactory orderFactory = new OrderFactory();
  

  static double shippingFees = 15;
  

  

  public OrderService() {
    
  }
  
  public boolean isOrderValid(HashMap<String, Integer> selectedProducts) {
    for (HashMap.Entry<String, Integer> entry : selectedProducts.entrySet()) {
      String serialNumber = entry.getKey();
      Integer quantity = entry.getValue();
      Product product = productsRepo.getProductsList().stream().filter(p -> p.getSerialNumber().equals(serialNumber)).findFirst().orElse(null);
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
    Customer customer = customersRepo.isUserExist(username);
    if (customer == null) {
      return null;
    }
    Order order = this.orderFactory.createOrder(orderType);

    for (HashMap.Entry<String, Integer> entry : selectedProducts.entrySet()) {
      String serialNumber = entry.getKey();
      Integer quantity = entry.getValue();
      Product product = productsRepo.getProductsList().stream().filter(p -> p.getSerialNumber().equals(serialNumber)).findFirst()
          .orElse(null);
      if(product == null)
        return null;
      order.addItem(product, quantity);
    }
    order.calcPrice();
    order.setCustomer(customer);
    customer.placeOrder(order);


    NotificationTemplate notificationTemplate = new PlacementTemplate(order);
    notificationQueueService.enqueueNotification(notificationTemplate);

    return order;
  }

  public Order findOrderByUsername(String username, int orderID){
    Customer customer = null;
    for(Customer ccustomer : customersRepo.getCustomersList()){
      if(ccustomer.getLoginData().getUsername().equals(username)){
       customer = ccustomer;
       break;
      }
    }
    Order order = null;
    List<Order> orders = customer.getOrders();
    for(Order currOrder : orders){
      if(currOrder.getOrderID() == orderID && currOrder.getOrderStatus() != OrderStatus.CANCELLED){
          order = currOrder;
          break;
      }
    }
    return order;
  }
  public Order createCompoundOrder(HashMap<String, Integer> selectedProducts, String orderType, String username, HashMap<String, Integer> simpleOrders){
    CompoundOrder compoundOrder = (CompoundOrder)createOrder(selectedProducts, "CompoundOrder", username); // TBD
    Customer customer = customersRepo.isUserExist(username);
   
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

  
  
  
  

  public ArrayList<String> getOrder(String username, int orderID){
    Order order = findOrderByUsername(username, orderID);
    return order.showDetails();
  }
  public boolean shipOrder(String username, int orderID){
    Order order = findOrderByUsername(username, orderID);
    if(order == null || order.getOrderStatus() != OrderStatus.PLACED)
      return false;

    order.shipOrder(shippingFees);

    NotificationTemplate notificationTemplate = new ShipmentTemplate(order);
    notificationQueueService.enqueueNotification(notificationTemplate);

    return true;
    
  }
}
