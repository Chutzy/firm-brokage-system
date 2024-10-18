package com.example.hubs.model;

import com.example.hubs.constants.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderModel {

    private Long customerId;
    private OrderStatus orderStatus;
}
