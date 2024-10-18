package com.example.hubs.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositMoneyRequest {

    private Long customerId;
    private Long amount;
}
