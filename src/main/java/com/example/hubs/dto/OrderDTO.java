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
    private Long size;
    private Long price;
    private String status;
    private LocalDateTime createDate;
}
