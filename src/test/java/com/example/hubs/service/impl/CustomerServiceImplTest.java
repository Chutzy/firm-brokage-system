package com.example.hubs.service.impl;

import com.example.hubs.entity.Customer;
import com.example.hubs.exception.CustomException;
import com.example.hubs.infra.MapperService;
import com.example.hubs.infra.MapperServiceImpl;
import com.example.hubs.model.CustomerModel;
import com.example.hubs.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    private final CustomerModel customerModel = new CustomerModel();
    private final Customer customer = new Customer();
    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Spy
    private MapperService mapper = new MapperServiceImpl();

    @BeforeEach
    public void setUp() {
        customer.setId(1L);
        customer.setRole("USER");
        customer.setUsername("admin");
        customer.setPassword("test123");

        customerModel.setUsername("admin");
        customerModel.setPassword("test123");
        customerModel.setRole("USER");
    }

    @Test
    public void loadUserByUsernameTest_Success() {
        when(customerRepository.findCustomerByUsername(any())).thenReturn(Optional.of(customer));
        Assertions.assertEquals("admin", customerService.loadUserByUsername("admin").getUsername());
    }

    @Test
    public void loadUserByUsernameTest_UserNotFound() {
        when(customerRepository.findCustomerByUsername(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(CustomException.class, () -> customerService.loadUserByUsername(any()));
    }

    @Test
    public void saveCustomerTest_Success() {
        Assertions.assertDoesNotThrow(() -> customerService.saveCustomer(customerModel));
    }

    @Test
    public void getCustomersTest_Success() {
        when(customerRepository.findCustomersByModel(customerModel)).thenReturn(Collections.singletonList(customer));
        Assertions.assertDoesNotThrow(() -> customerService.getCustomers(customerModel));
    }

    @Test
    public void getCustomerByUsernameTest_Success() {
        when(customerRepository.findCustomerByUsername(any())).thenReturn(Optional.of(customer));
        Assertions.assertDoesNotThrow(() -> customerService.getCustomerByUsername("admin"));
    }
}
