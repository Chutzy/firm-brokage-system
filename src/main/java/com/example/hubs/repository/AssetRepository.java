package com.example.hubs.repository;

import com.example.hubs.entity.Asset;
import com.example.hubs.model.ListAssetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {

    @Query(value = "SELECT a from Asset a where " +
            "(a.customerId = :#{#model.customerId})" +
            "and (a.assetName is null or a.assetName = :#{#model.assetName})" +
            "and (a.assetSize is null or a.assetSize = :#{#model.assetSize})" +
            "and (a.usableSize is null or a.usableSize = :#{#model.usableSize})")
    List<Asset> findAssetsByModel(ListAssetModel model);
}
