package com.example.hubs.api;

import com.example.hubs.request.AuthenticationRequest;
import com.example.hubs.response.CustomerResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthenticationApi {

    @PostMapping("/login")
    String login(AuthenticationRequest request);

    @PostMapping("/register")
    CustomerResponse register(AuthenticationRequest request);

}
