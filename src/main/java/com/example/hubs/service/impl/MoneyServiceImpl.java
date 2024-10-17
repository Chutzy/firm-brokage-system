package com.example.hubs.service.impl;

import com.example.hubs.entity.Money;
import com.example.hubs.model.DepositMoneyModel;
import com.example.hubs.model.DepositMoneyResponseModel;
import com.example.hubs.model.WithdrawMoneyModel;
import com.example.hubs.model.WithdrawMoneyResponseModel;
import com.example.hubs.repository.MoneyRepository;
import com.example.hubs.service.MoneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.hubs.constants.TransactionTypes.DEPOSIT;

@Service
@RequiredArgsConstructor
public class MoneyServiceImpl implements MoneyService {

    private final MoneyRepository repository;

    @Override
    public WithdrawMoneyResponseModel withdraw(WithdrawMoneyModel model) {
        return null;
    }

    @Override
    public DepositMoneyResponseModel deposit(DepositMoneyModel model) {
        Money money = repository.findByCustomerId(model.getCustomerId()).orElse(new Money());
        money.setAmount(money.getAmount().add(model.getAmount()));
        money.setTransactionType(DEPOSIT.getValue());
        money.setCustomerId(money.getCustomerId() == null ? model.getCustomerId() : money.getCustomerId());
        money.setUpdateTime(LocalDateTime.now());
        repository.save(money);
        return new DepositMoneyResponseModel(money.getCustomerId(), money.getAmount());
    }
}
