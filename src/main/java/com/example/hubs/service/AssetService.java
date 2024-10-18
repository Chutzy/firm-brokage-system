package com.example.hubs.service;

import com.example.hubs.model.*;

public interface AssetService {

    ListAssetResponseModel list(ListAssetModel model);

    WithdrawMoneyResponseModel withdrawMoney(WithdrawMoneyModel model);

    DepositMoneyResponseModel depositMoney(DepositMoneyModel model);
}
