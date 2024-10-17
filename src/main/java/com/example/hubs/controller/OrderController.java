package com.example.hubs.controller;

import com.example.hubs.api.OrderApi;
import com.example.hubs.infra.MapperService;
import com.example.hubs.model.CreateOrderModel;
import com.example.hubs.model.ListOrderModel;
import com.example.hubs.model.UpdateOrderModel;
import com.example.hubs.request.CreateOrderRequest;
import com.example.hubs.request.ListOrderRequest;
import com.example.hubs.request.UpdateOrderRequest;
import com.example.hubs.response.BaseResponse;
import com.example.hubs.response.ListOrderResponse;
import com.example.hubs.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi {

    private final OrderService orderService;
    private final MapperService mapper;

    @Override
    public ListOrderResponse list(ListOrderRequest request) {
        ListOrderModel listOrderModel = mapper.map(request, ListOrderModel.class);
        listOrderModel.setStartDate(request.getStartDate().atStartOfDay());
        listOrderModel.setEndDate(request.getEndDate().atStartOfDay());
        return mapper.map(orderService.list(listOrderModel), ListOrderResponse.class);
    }

    @Override
    public BaseResponse create(CreateOrderRequest request) {
        orderService.create(mapper.map(request, CreateOrderModel.class));
        return new BaseResponse();
    }

    @Override
    public BaseResponse update(UpdateOrderRequest request) {
        orderService.update(mapper.map(request, UpdateOrderModel.class));
        return new BaseResponse();
    }
}
