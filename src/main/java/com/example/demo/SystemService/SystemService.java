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
public class SystemService {

  @Autowired
  private CustomersRepo customersRepo;
  @Autowired
  private ProductsRepo productsRepo;
  
  NotificationQueueService notificationQueueService = new NotificationQueueService();
  private OrderFactory orderFactory = new OrderFactory();
  public ArrayList<String> array = new ArrayList<>();

  static double shippingFees = 15;
  

  

  public SystemService() {
    
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

  private Order findOrderByUsername(String username, int orderID){
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
      if(currOrder.getOrderID() == orderID){
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
      for(Product product : productsRepo.getProductsList()){
        if(product.getSerialNumber().equals(entry.getKey().getSerialNumber())){
          product.setRemainingQuantity(product.getRemainingQuantity() - entry.getValue());
        }
      }
    }
  }

  private void increaseQuantity(HashMap<Product, Integer> hashMap){
    for(HashMap.Entry<Product, Integer> entry : hashMap.entrySet()){
      for(Product product : productsRepo.getProductsList()){
        if(product.getSerialNumber().equals(entry.getKey().getSerialNumber())){
          product.setRemainingQuantity(product.getRemainingQuantity() + entry.getValue());
        }
      }
    }
  }

  public double getCustomerBalance(String username){
    for (Customer customer : customersRepo.getCustomersList()) {
      if (customer.getLoginData().getUsername().equals(username)) {
          return customer.getCustomerData().getBalance();
      }
    }
    return -1;
  }
  public int getProductsQuantity(String serialnumber){
    for(Product product : productsRepo.getProductsList()){
        if(product.getSerialNumber().equals(serialnumber)){
          return product.getRemainingQuantity();
        }
      }
      return -1;
  }
  public boolean cancelOrder(String username, int orderID){
    Order order = findOrderByUsername(username, orderID);
    if(order == null)
      return false;
    double CustomerBalance = order.getCustomer().getCustomerData().getBalance();
    double orderPrice = order.getTotalAmount();
    order.getCustomer().getCustomerData().setBalance(CustomerBalance + orderPrice);
    HashMap<Product, Integer> hashMap = order.getAllProductsQuantity();
    increaseQuantity(hashMap);
    return true;
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
  public boolean isUserValid(String username, String password){
    for(Customer customer : customersRepo.getCustomersList()){
      if(customer.getLoginData().getUsername().equals(username)){
        if(customer.getLoginData().getPassword().equals(password)){
          return true;
        }
      }
    }
    return false;
  }
    @Scheduled(fixedRate = 5000)
    public void removeMessagesFromQueue() {
        NotificationTemplate notificationTemplate = notificationQueueService.dequeue();
        if(notificationTemplate == null)
          return;
        notificationTemplate.getCustomer().getNotifier().send(notificationTemplate);
        array.add(notificationTemplate.getContent());
    }
}
