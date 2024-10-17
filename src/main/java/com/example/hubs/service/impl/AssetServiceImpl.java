package com.example.hubs.service.impl;

import com.example.hubs.entity.Asset;
import com.example.hubs.model.ListAssetModel;
import com.example.hubs.model.ListAssetResponseModel;
import com.example.hubs.repository.AssetRepository;
import com.example.hubs.service.AssetService;
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
}
