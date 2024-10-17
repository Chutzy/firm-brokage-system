package com.example.hubs.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {
    PENDING("P"),
    MATCHED("M"),
    CANCELLED("C");

    private final String value;
}
