package com.example.hubs.controller;

import com.example.hubs.api.AssetApi;
import com.example.hubs.infra.MapperService;
import com.example.hubs.model.ListAssetModel;
import com.example.hubs.request.ListAssetRequest;
import com.example.hubs.response.ListAssetResponse;
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
}
