package com.example.hubs.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {

    @NotNull(message = "customerId cannot be null")
    private Long customerId;
    @NotBlank(message = "assetName cannot be blank")
    private String assetName;
    @NotBlank(message = "orderSide cannot be blank")
    @Pattern(regexp = "^[BS]$", message = "orderSide can be either B or S")
    private String orderSide;
    @NotBlank(message = "size cannot be blank")
    private String size;
    @NotBlank(message = "price cannot be blank")
    private String price;
}
