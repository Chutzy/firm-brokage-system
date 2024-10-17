package com.example.hubs.service;

import com.example.hubs.model.DepositMoneyModel;
import com.example.hubs.model.DepositMoneyResponseModel;
import com.example.hubs.model.WithdrawMoneyModel;
import com.example.hubs.model.WithdrawMoneyResponseModel;

public interface MoneyService {

    WithdrawMoneyResponseModel withdraw(WithdrawMoneyModel model);

    DepositMoneyResponseModel deposit(DepositMoneyModel model);
}
