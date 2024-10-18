package com.example.hubs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ListAssetModel {

    private Long customerId;
    private String assetName;
    private String assetSize;
    private Long usableSize;
}
