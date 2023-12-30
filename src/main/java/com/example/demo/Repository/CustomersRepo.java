package com.example.demo.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.models.Customer;

@Repository
public class CustomersRepo {
    private List<Customer> customersList = new ArrayList<>();
    public List<Customer> getCustomersList(){
        return customersList;
    }
    public void addCustomer(Customer customer) {
        customersList.add(customer);
    }
    public Customer isUserExist(String username) {
        return customersList.stream().filter(customer -> customer.getLoginData().getUsername().equals(username))
                .findFirst().orElse(null);
    }
}
