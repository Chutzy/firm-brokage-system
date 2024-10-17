package com.example.hubs.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositMoneyResponse extends BaseResponse {

    private Long customerId;
    private BigDecimal amount;
}
