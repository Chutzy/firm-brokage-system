package com.example.hubs.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListAssetRequest {

    @NotNull(message = "customerId cannot be null")
    private Long customerId;
    private String assetName;
    private String assetSize;
    private String usableSize;
}
