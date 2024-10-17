package com.example.hubs.controller;

import com.example.hubs.api.MoneyApi;
import com.example.hubs.infra.MapperService;
import com.example.hubs.model.DepositMoneyModel;
import com.example.hubs.model.WithdrawMoneyModel;
import com.example.hubs.request.DepositMoneyRequest;
import com.example.hubs.request.WithdrawMoneyRequest;
import com.example.hubs.response.DepositMoneyResponse;
import com.example.hubs.response.WithdrawMoneyResponse;
import com.example.hubs.service.MoneyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MoneyController implements MoneyApi {

    private final MoneyService moneyService;
    private final MapperService mapper;

    @Override
    public WithdrawMoneyResponse withdraw(WithdrawMoneyRequest request) {
        return mapper.map(moneyService.withdraw(mapper.map(request, WithdrawMoneyModel.class)), WithdrawMoneyResponse.class);
    }

    @Override
    public DepositMoneyResponse deposit(@RequestBody @Valid DepositMoneyRequest request) {
        return mapper.map(moneyService.deposit(mapper.map(request, DepositMoneyModel.class)), DepositMoneyResponse.class);
    }
}
