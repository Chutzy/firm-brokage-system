package com.example.hubs.api;

import com.example.hubs.request.DepositMoneyRequest;
import com.example.hubs.request.WithdrawMoneyRequest;
import com.example.hubs.response.DepositMoneyResponse;
import com.example.hubs.response.WithdrawMoneyResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/money")
public interface MoneyApi {

    @PostMapping("/deposit")
    DepositMoneyResponse deposit(@RequestBody DepositMoneyRequest depositMoneyRequest);

    @PostMapping("/withdraw")
    WithdrawMoneyResponse withdraw(@RequestBody WithdrawMoneyRequest withdrawMoneyRequest);
}
