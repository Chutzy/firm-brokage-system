package com.example.hubs.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListAssetModel {

    private Long customerId;
    private String assetName;
    private String assetSize;
    private String usableSize;
}
