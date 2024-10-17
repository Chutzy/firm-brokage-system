package com.example.hubs.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderModel {

    private Long customerId;
    private String assetName;
    private String orderSide;
    private String size;
    private String price;
}
