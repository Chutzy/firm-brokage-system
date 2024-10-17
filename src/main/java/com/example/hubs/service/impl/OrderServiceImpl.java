package com.example.hubs.service.impl;

import com.example.hubs.constants.OrderStatus;
import com.example.hubs.entity.Order;
import com.example.hubs.exception.CustomException;
import com.example.hubs.model.CreateOrderModel;
import com.example.hubs.model.ListOrderModel;
import com.example.hubs.model.ListOrderResponseModel;
import com.example.hubs.model.UpdateOrderModel;
import com.example.hubs.repository.OrderRepository;
import com.example.hubs.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.hubs.constants.Constants.OrderConstants.ORDER_SIDE_BUY;
import static com.example.hubs.constants.Constants.OrderConstants.ORDER_SIDE_SELL;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public ListOrderResponseModel list(ListOrderModel model) {
        return null;
    }

    @Override
    public void create(CreateOrderModel model) {
        if (!(model.getOrderSide().equals(ORDER_SIDE_BUY) || model.getOrderSide().equals(ORDER_SIDE_SELL))) {
            throw new CustomException("Order Side must be either 'B' or 'S'");
        }
        saveNewOrder(model);
    }


    @Override
    public void update(UpdateOrderModel model) {

    }

    private void saveNewOrder(CreateOrderModel model) {
        Order order = mapper.map(model, Order.class);
        order.setStatus(OrderStatus.PENDING.getValue());
        order.setCreateDate(LocalDateTime.now());
        orderRepository.save(order);
    }
}
