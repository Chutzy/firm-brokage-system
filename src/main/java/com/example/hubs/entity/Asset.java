package com.example.hubs.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(schema = "HUBS", name = "ASSET")
public class Asset {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long customerId;

    @Column(name = "ASSET_NAME", nullable = false)
    private String assetName;

    @Column(name = "SIZE", nullable = false)
    private Long assetSize;

    @Column(name = "USABLE_SIZE", nullable = false)
    private Long usableSize;
}
