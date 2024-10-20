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
    private Long assetSize;
    private Long usableSize;
}
