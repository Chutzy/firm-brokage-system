package com.example.hubs.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderModel {

    private Long customerId;
    private String assetName;
    private String orderSide;
    private Long size;
    private Long price;
}
