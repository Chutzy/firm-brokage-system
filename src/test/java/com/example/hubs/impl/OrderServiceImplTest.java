package com.example.hubs.impl;

import com.example.hubs.entity.Order;
import com.example.hubs.exception.CustomException;
import com.example.hubs.model.CreateOrderModel;
import com.example.hubs.model.ListOrderModel;
import com.example.hubs.model.UpdateOrderModel;
import com.example.hubs.repository.OrderRepository;
import com.example.hubs.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.hubs.constants.Constants.OrderConstants.ORDER_SIDE_BUY;
import static com.example.hubs.constants.OrderStatus.PENDING;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    private final ListOrderModel listOrderModel = new ListOrderModel();
    private final List<Order> orderList = new ArrayList<>();
    private final Order order = new Order();
    private final CreateOrderModel createOrderModel = new CreateOrderModel();
    private final UpdateOrderModel updateOrderModel = new UpdateOrderModel();
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        listOrderModel.setStartDate(LocalDateTime.now().minusDays(3L));
        listOrderModel.setCustomerId(1L);
        listOrderModel.setStatus(PENDING.getValue());
        listOrderModel.setPrice(12L);
        listOrderModel.setEndDate(LocalDateTime.now());
        listOrderModel.setAssetName("test");

        order.setCustomerId(1L);
        order.setCreateTime(LocalDateTime.now());
        order.setStatus(PENDING.getValue());
        order.setPrice(12L);
        order.setId(1L);
        order.setSize(10L);
        order.setOrderSide(ORDER_SIDE_BUY);
        orderList.add(order);
    }

    @Test
    void listOrderTest_Successful() {
        when(orderRepository.findOrdersByCriteria(listOrderModel)).thenReturn(orderList);
        Assertions.assertEquals(12L, orderService.list(listOrderModel).getOrderList().get(0).getPrice());
    }

    @Test
    void listOrderTest_OrderListEmpty() {
        when(orderRepository.findOrdersByCriteria(listOrderModel)).thenReturn(new ArrayList<>());
        Assertions.assertThrows(CustomException.class, () -> orderService.list(listOrderModel).getOrderList().size());
    }

}
