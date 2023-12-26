package com.example.demo.SystemService;

import com.example.demo.models.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private List<Customer> customersList = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customersList.add(customer);
    }

    public boolean isUserExist(String username) {
        return customersList.stream().anyMatch(c -> c.getUsername().equals(username));
    }
}
