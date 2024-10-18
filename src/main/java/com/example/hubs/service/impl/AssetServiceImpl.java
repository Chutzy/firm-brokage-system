package com.example.hubs.service.impl;

import com.example.hubs.constants.AssetName;
import com.example.hubs.entity.Asset;
import com.example.hubs.exception.CustomException;
import com.example.hubs.model.*;
import com.example.hubs.repository.AssetRepository;
import com.example.hubs.service.AssetService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    @Override
    public ListAssetResponseModel list(ListAssetModel model) {
        List<Asset> assetList = assetRepository.findAssetsByModel(model);
        return ListAssetResponseModel.builder().assetList(assetList).build();
    }

    @Override
    public WithdrawMoneyResponseModel withdrawMoney(WithdrawMoneyModel model) {
        Asset asset = assetRepository.findAssetByCustomerIdAndAssetName(model.getCustomerId(), AssetName.TRY.getAssetName()).orElseThrow(() -> new CustomException("asset not found for customer"));
        if (asset.getAssetSize() < model.getAmount()) {
            throw new CustomException("not enough money");
        }
        asset.setAssetSize(asset.getAssetSize() - model.getAmount());
        asset.setUsableSize(asset.getUsableSize() - model.getAmount());
        assetRepository.save(asset);
        WithdrawMoneyResponseModel withdrawMoneyResponseModel = new WithdrawMoneyResponseModel();
        withdrawMoneyResponseModel.setUsableAmount(asset.getUsableSize().toString());
        return withdrawMoneyResponseModel;
    }

    @Transactional
    @Override
    public DepositMoneyResponseModel depositMoney(DepositMoneyModel model) {
        DepositMoneyResponseModel depositMoneyResponseModel = new DepositMoneyResponseModel();
        assetRepository.findAssetByCustomerId(model.getCustomerId())
                .ifPresentOrElse(asset -> {
                            asset.setAssetSize(asset.getAssetSize() + model.getAmount());
                            asset.setUsableSize(asset.getUsableSize() + model.getAmount());
                            assetRepository.save(asset);
                            depositMoneyResponseModel.setUsableAmount(asset.getUsableSize());
                        },
                        () -> {
                            Asset asset = new Asset();
                            asset.setCustomerId(model.getCustomerId());
                            asset.setAssetName(AssetName.TRY.getAssetName());
                            asset.setAssetSize(model.getAmount());
                            asset.setUsableSize(model.getAmount());
                            assetRepository.save(asset);
                            depositMoneyResponseModel.setUsableAmount(asset.getUsableSize());
                        });
        return depositMoneyResponseModel;
    }
}
