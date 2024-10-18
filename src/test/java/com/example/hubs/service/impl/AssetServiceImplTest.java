package com.example.hubs.service.impl;

import com.example.hubs.constants.AssetName;
import com.example.hubs.entity.Asset;
import com.example.hubs.exception.CustomException;
import com.example.hubs.model.DepositMoneyModel;
import com.example.hubs.model.ListAssetModel;
import com.example.hubs.model.WithdrawMoneyModel;
import com.example.hubs.repository.AssetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetServiceImplTest {

    private final WithdrawMoneyModel withdrawMoneyModel = new WithdrawMoneyModel();
    private final DepositMoneyModel depositMoneyModel = new DepositMoneyModel();
    private final Asset asset = new Asset();
    @InjectMocks
    private AssetServiceImpl assetService;
    @Mock
    private AssetRepository assetRepository;

    @BeforeEach
    public void setUp() {
        asset.setCustomerId(1L);
        asset.setAssetSize(150L);
        asset.setUsableSize(150L);
        asset.setAssetName(AssetName.TRY.getAssetName());

        depositMoneyModel.setAmount(100L);
        depositMoneyModel.setCustomerId(1L);

        withdrawMoneyModel.setAmount(50L);
        withdrawMoneyModel.setCustomerId(1L);
    }

    @Test
    public void depositMoneyTest_Success() {
        when(assetRepository.findAssetByCustomerId(depositMoneyModel.getCustomerId())).thenReturn(Optional.of(asset));
        Assertions.assertEquals(250L, assetService.depositMoney(depositMoneyModel).getUsableAmount());
    }

    @Test
    public void depositMoneyTest_NewAsset() {
        Assertions.assertEquals(100L, assetService.depositMoney(depositMoneyModel).getUsableAmount());
    }

    @Test
    public void withdrawMoneyTest_Success() {
        when(assetRepository.findAssetByCustomerIdAndAssetName(withdrawMoneyModel.getCustomerId(), AssetName.TRY.getAssetName())).thenReturn(Optional.of(asset));
        Assertions.assertEquals("100", assetService.withdrawMoney(withdrawMoneyModel).getUsableAmount());
    }

    @Test
    public void withdrawMoneyTest_InsufficientFunds() {
        asset.setAssetSize(0L);
        asset.setUsableSize(0L);
        when(assetRepository.findAssetByCustomerIdAndAssetName(withdrawMoneyModel.getCustomerId(), AssetName.TRY.getAssetName())).thenReturn(Optional.of(asset));
        Assertions.assertThrows(CustomException.class, () -> assetService.withdrawMoney(withdrawMoneyModel));
    }

    @Test
    public void listAssetsTest_Success() {
        ListAssetModel listAssetModel = new ListAssetModel(1L, AssetName.TRY.getAssetName(), 150L, 150L);
        when(assetRepository.findAssetsByModel(listAssetModel)).thenReturn(List.of(asset));
        Assertions.assertEquals(150L, assetService.list(listAssetModel).getAssetList().get(0).getUsableSize());
    }
}
