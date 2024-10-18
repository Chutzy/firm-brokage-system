package com.example.hubs.response;

import com.example.hubs.dto.CustomerDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CustomerListResponse {

    private List<CustomerDTO> customerDTOList = new ArrayList<>();
}
