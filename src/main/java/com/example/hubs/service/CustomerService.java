package com.example.hubs.service;

import com.example.hubs.entity.Customer;
import com.example.hubs.model.CustomerModel;
import com.example.hubs.model.CustomerResponseModel;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    UserDetails loadUserByUsername(String username);

    List<CustomerResponseModel> getCustomers(CustomerModel model);

    Optional<Customer> getCustomerByUsername(String username);

    void saveCustomer(CustomerModel model);
}
