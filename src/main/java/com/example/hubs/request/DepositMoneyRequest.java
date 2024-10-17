package com.example.hubs.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositMoneyRequest {

    private Long customerId;
    private BigDecimal amount;
}
