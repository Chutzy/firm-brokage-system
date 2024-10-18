package com.example.hubs.service;

import com.example.hubs.model.CustomerModel;
import com.example.hubs.model.CustomerResponseModel;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface CustomerService {

    UserDetails loadCustomersByUsername(String username);

    List<CustomerResponseModel> getCustomers(CustomerModel model);
}
