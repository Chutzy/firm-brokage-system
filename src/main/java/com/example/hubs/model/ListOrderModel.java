package com.example.hubs.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ListOrderModel {

    private Long customerId;
    private String assetName;
    private String orderSide;
    private Long size;
    private Long price;
    private String status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
