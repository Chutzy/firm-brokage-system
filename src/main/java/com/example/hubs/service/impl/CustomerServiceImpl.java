package com.example.hubs.service.impl;

import com.example.hubs.entity.Customer;
import com.example.hubs.exception.CustomException;
import com.example.hubs.infra.MapperService;
import com.example.hubs.model.CustomerModel;
import com.example.hubs.model.CustomerResponseModel;
import com.example.hubs.repository.CustomerRepository;
import com.example.hubs.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final MapperService mapper;

    @Override
    public UserDetails loadCustomersByUsername(String username) {
        Customer customer = customerRepository.findCustomerByUsername(username).orElseThrow(() -> new CustomException("Customer not found"));
        return new User(customer.getUsername(), customer.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(customer.getRole())));
    }

    @Override
    public List<CustomerResponseModel> getCustomers(CustomerModel model) {
        List<CustomerResponseModel> customerResponseModelList = new ArrayList<>();
        customerRepository.findCustomersByModel(model).forEach(customer -> customerResponseModelList.add(mapper.map(customer, CustomerResponseModel.class)));
        return customerResponseModelList;
    }
}
