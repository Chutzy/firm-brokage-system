package com.example.hubs.model;

import com.example.hubs.constants.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateOrderModel {

    private Long customerId;
    private OrderStatus orderStatus;
}
