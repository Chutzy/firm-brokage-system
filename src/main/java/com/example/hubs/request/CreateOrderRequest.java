package com.example.hubs.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {

    @NotNull(message = "customerId cannot be null")
    private Long customerId;
    @NotEmpty(message = "assetName cannot be empty")
    private String assetName;
    @NotEmpty(message = "orderSide cannot be empty")
    @Pattern(regexp = "^[BS]$", message = "orderSide can be either B or S")
    private String orderSide;
    @NotEmpty(message = "size cannot be empty")
    private String size;
    @NotEmpty(message = "price cannot be empty")
    private String price;
}
