package com.example.hubs.api;

import com.example.hubs.request.ListAssetsRequest;
import com.example.hubs.response.ListAssetResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/asset")
public interface AssetApi {

    @GetMapping("/list")
    ListAssetResponse list(@RequestBody ListAssetsRequest request);
}
