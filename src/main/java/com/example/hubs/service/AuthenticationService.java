package com.example.hubs.service;

import com.example.hubs.model.AuthenticationModel;
import com.example.hubs.model.CustomerModel;

public interface AuthenticationService {

    String login(AuthenticationModel model);

    CustomerModel register(AuthenticationModel model);
}
