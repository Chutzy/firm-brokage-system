package com.example.hubs.api;

import com.example.hubs.request.DepositMoneyRequest;
import com.example.hubs.request.ListAssetRequest;
import com.example.hubs.request.WithdrawMoneyRequest;
import com.example.hubs.response.DepositMoneyResponse;
import com.example.hubs.response.ListAssetResponse;
import com.example.hubs.response.WithdrawMoneyResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/asset")
public interface AssetApi {

    @GetMapping("/list")
    ListAssetResponse list(@RequestBody @Valid ListAssetRequest request);

    @PostMapping("/deposit")
    DepositMoneyResponse depositMoney(@RequestBody @Valid DepositMoneyRequest request);

    @PostMapping("/withdraw")
    WithdrawMoneyResponse withdrawMoney(@RequestBody @Valid WithdrawMoneyRequest request);

}
