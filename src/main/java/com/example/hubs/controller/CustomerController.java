package com.example.hubs.controller;

import com.example.hubs.api.CustomerApi;
import com.example.hubs.infra.MapperService;
import com.example.hubs.model.CustomerModel;
import com.example.hubs.request.CustomerListRequest;
import com.example.hubs.response.CustomerListResponse;
import com.example.hubs.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController implements CustomerApi {

    private final CustomerService customerService;
    private final MapperService mapper;

    @Override
    public CustomerListResponse list(CustomerListRequest request) {
        return mapper.map(customerService.getCustomers(mapper.map(request, CustomerModel.class)), CustomerListResponse.class);
    }
}
