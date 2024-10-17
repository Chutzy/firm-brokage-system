package com.example.hubs.service;

import com.example.hubs.model.CreateOrderModel;
import com.example.hubs.model.ListOrderModel;
import com.example.hubs.model.ListOrderResponseModel;
import com.example.hubs.model.UpdateOrderModel;

public interface OrderService {

    ListOrderResponseModel list(ListOrderModel model);

    void create(CreateOrderModel model);

    void update(UpdateOrderModel model);
}
