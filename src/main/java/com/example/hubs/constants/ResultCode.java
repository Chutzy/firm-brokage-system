package com.example.hubs.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultCode {
    ERROR("0", "Error"),
    SUCCESS("1", "Successful");

    private final String code;
    private final String value;
}
