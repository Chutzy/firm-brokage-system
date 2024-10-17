package com.example.hubs.response;

import com.example.hubs.dto.OrderDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ListOrderResponse {

    private List<OrderDTO> orderDTOList = new ArrayList<>();
}
