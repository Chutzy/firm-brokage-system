package com.example.hubs.api;

import com.example.hubs.request.CreateOrderRequest;
import com.example.hubs.request.ListOrderRequest;
import com.example.hubs.request.UpdateOrderRequest;
import com.example.hubs.response.BaseResponse;
import com.example.hubs.response.ListOrderResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
public interface OrderApi {

    @GetMapping("/list")
    ListOrderResponse list(ListOrderRequest request);

    @PostMapping("/create")
    BaseResponse create(@RequestBody @Validated CreateOrderRequest request);

    @PutMapping("/update")
    BaseResponse update(UpdateOrderRequest request);
}
