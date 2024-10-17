package com.example.hubs.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionTypes {
    DEPOSIT("D"),
    WITHDRAWAL("W");

    private final String value;

}
