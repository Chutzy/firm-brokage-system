package com.example.hubs.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderRequest {

    @NotNull(message = "order id cannot be empty")
    private Long id;
    @NotBlank
    @Pattern(regexp = "^[MC]$", message = "order status can only be M or C")
    private String updateStatus;
}
