package com.example.hubs.service.impl;

import com.example.hubs.constants.Constants;
import com.example.hubs.exception.CustomException;
import com.example.hubs.model.AuthenticationModel;
import com.example.hubs.model.CustomerModel;
import com.example.hubs.service.AuthenticationService;
import com.example.hubs.service.CustomerService;
import com.example.hubs.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final CustomerService customerService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public String login(AuthenticationModel model) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(model.getUsername());
        return jwtTokenUtil.generateToken(userDetails.getUsername());
    }

    @Override
    public CustomerModel register(AuthenticationModel model) {
        customerService.getCustomerByUsername(model.getUsername())
                .ifPresent(c -> {
                    throw new CustomException("Customer already exists");
                });
        CustomerModel customerModel = new CustomerModel();
        customerModel.setUsername(model.getUsername());
        customerModel.setPassword(bCryptPasswordEncoder.encode(model.getPassword()));
        customerModel.setRole(Constants.CustomerConstants.ROLE_USER);
        customerService.saveCustomer(customerModel);
        return customerModel;
    }
}
