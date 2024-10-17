package com.example.hubs.model;

import com.example.hubs.entity.Asset;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ListAssetResponseModel {

    List<Asset> assetList;
}
