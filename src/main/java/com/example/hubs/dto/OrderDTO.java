package com.example.hubs.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDTO {

    private Long id;
    private Long customerId;
    private String assetName;
    private String orderSide;
    private String size;
    private String price;
    private String status;
    private LocalDateTime createDate;
}
