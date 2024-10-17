package com.example.hubs.service.impl;

import com.example.hubs.entity.Order;
import com.example.hubs.model.CreateOrderModel;
import com.example.hubs.model.ListOrderModel;
import com.example.hubs.model.ListOrderResponseModel;
import com.example.hubs.model.UpdateOrderModel;
import com.example.hubs.repository.OrderRepository;
import com.example.hubs.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.hubs.constants.OrderStatus.CANCELLED;
import static com.example.hubs.constants.OrderStatus.PENDING;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public ListOrderResponseModel list(ListOrderModel model) {
        List<Order> orderList = orderRepository.findOrdersByCriteria(model);
        ListOrderResponseModel listOrderResponseModel = new ListOrderResponseModel();
        listOrderResponseModel.setOrderList(orderList);
        return listOrderResponseModel;
    }

    @Override
    public void create(CreateOrderModel model) {
        saveNewOrder(model);
    }

    @Transactional
    @Override
    public void update(UpdateOrderModel model) {
        orderRepository.updatePendingOrderByOrderIdAndStatus(model.getOrderId(), CANCELLED.getValue());
    }

    private void saveNewOrder(CreateOrderModel model) {
        Order order = mapper.map(model, Order.class);
        order.setStatus(PENDING.getValue());
        order.setCreateDate(LocalDateTime.now());
        orderRepository.save(order);
    }
}
