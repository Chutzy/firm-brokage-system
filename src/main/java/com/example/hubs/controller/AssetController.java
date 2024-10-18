package com.example.hubs.controller;

import com.example.hubs.api.AssetApi;
import com.example.hubs.infra.MapperService;
import com.example.hubs.model.DepositMoneyModel;
import com.example.hubs.model.ListAssetModel;
import com.example.hubs.model.WithdrawMoneyModel;
import com.example.hubs.request.DepositMoneyRequest;
import com.example.hubs.request.ListAssetRequest;
import com.example.hubs.request.WithdrawMoneyRequest;
import com.example.hubs.response.DepositMoneyResponse;
import com.example.hubs.response.ListAssetResponse;
import com.example.hubs.response.WithdrawMoneyResponse;
import com.example.hubs.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AssetController implements AssetApi {

    private final AssetService assetService;
    private final MapperService mapper;

    @Override
    public ListAssetResponse list(ListAssetRequest request) {
        return mapper.map(assetService.list(mapper.map(request, ListAssetModel.class)), ListAssetResponse.class);
    }

    @Override
    public DepositMoneyResponse depositMoney(DepositMoneyRequest request) {
        return mapper.map(assetService.depositMoney(mapper.map(request, DepositMoneyModel.class)), DepositMoneyResponse.class);
    }

    @Override
    public WithdrawMoneyResponse withdrawMoney(WithdrawMoneyRequest request) {
        return mapper.map(assetService.withdrawMoney(mapper.map(request, WithdrawMoneyModel.class)), WithdrawMoneyResponse.class);
    }
}
