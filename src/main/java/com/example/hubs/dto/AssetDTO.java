package com.example.hubs.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssetDTO {

    private Long id;
    private Long customerId;
    private String assetName;
    private String size;
    private String usableSize;
}
