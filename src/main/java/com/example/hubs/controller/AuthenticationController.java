package com.example.hubs.controller;

import com.example.hubs.api.AuthenticationApi;
import com.example.hubs.infra.MapperService;
import com.example.hubs.model.AuthenticationModel;
import com.example.hubs.request.AuthenticationRequest;
import com.example.hubs.response.CustomerResponse;
import com.example.hubs.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApi {

    private final AuthenticationService authenticationService;
    private final MapperService mapper;

    @Override
    public String login(AuthenticationRequest request) {
        return authenticationService.login(mapper.map(request, AuthenticationModel.class));
    }

    @Override
    public CustomerResponse register(AuthenticationRequest request) {
        return mapper.map(authenticationService.register(mapper.map(request, AuthenticationModel.class)), CustomerResponse.class);
    }
}
