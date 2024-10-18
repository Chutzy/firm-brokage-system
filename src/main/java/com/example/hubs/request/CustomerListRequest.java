package com.example.hubs.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerListRequest {

    private String id;
    private String name;
    private String surname;
    private String username;
    private String role;
}
