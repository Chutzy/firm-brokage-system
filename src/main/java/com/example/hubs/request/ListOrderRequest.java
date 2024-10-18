package com.example.hubs.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ListOrderRequest {

    @NotBlank(message = "customerId cannot be blank")
    private Long customerId;
    private String assetName;
    private String orderSide;
    private Long size;
    private Long price;
    private String status;
    @NotNull(message = "startDate cannot be null")
    private LocalDate startDate;
    @NotNull(message = "endDate cannot be null")
    private LocalDate endDate;
}
