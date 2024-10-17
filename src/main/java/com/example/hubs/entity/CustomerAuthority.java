package com.example.hubs.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(schema = "HUBS", name = "CUSTOMER_AUTHORITY")
public class CustomerAuthority {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "AUTHORITY")
    private String authority;

    @Column(name = "ENDPOINT")
    private String endpoint;
}
