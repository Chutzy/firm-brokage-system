package com.example.hubs.response;

import com.example.hubs.dto.AssetDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ListAssetResponse {

    List<AssetDTO> assetDTOList = new ArrayList<>();
}
