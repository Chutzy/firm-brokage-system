package com.example.hubs.service;

import com.example.hubs.model.ListAssetModel;
import com.example.hubs.model.ListAssetResponseModel;

public interface AssetService {

    ListAssetResponseModel list(ListAssetModel model);
}
