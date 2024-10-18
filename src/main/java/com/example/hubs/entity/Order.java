package com.example.hubs.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(schema = "HUBS", name = "ORDER")
public class Order {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long customerId;

    @Column(name = "ASSET_NAME", nullable = false)
    private String assetName;

    @Column(name = "ORDER_SIDE", nullable = false)
    private String orderSide;

    @Column(name = "SIZE", nullable = false)
    private Long size;

    @Column(name = "PRICE", nullable = false)
    private Long price;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;

    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;
}
