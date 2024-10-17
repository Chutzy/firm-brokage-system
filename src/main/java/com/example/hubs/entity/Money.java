package com.example.hubs.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(schema = "HUBS", name = "MONEY")
public class Money {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;

    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;
}
