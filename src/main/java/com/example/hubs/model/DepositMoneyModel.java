package com.example.hubs.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class DepositMoneyModel {

    private Long customerId;
    private BigDecimal amount;
    private String transactionType;
    private LocalDateTime updateTime;
}
