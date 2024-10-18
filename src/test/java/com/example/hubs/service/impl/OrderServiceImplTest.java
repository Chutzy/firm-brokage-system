package com.example.hubs.service.impl;

import com.example.hubs.entity.Asset;
import com.example.hubs.entity.Order;
import com.example.hubs.exception.CustomException;
import com.example.hubs.infra.MapperServiceImpl;
import com.example.hubs.model.CreateOrderModel;
import com.example.hubs.model.ListOrderModel;
import com.example.hubs.model.UpdateOrderModel;
import com.example.hubs.repository.AssetRepository;
import com.example.hubs.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.hubs.constants.Constants.OrderConstants.ORDER_SIDE_BUY;
import static com.example.hubs.constants.Constants.OrderConstants.ORDER_SIDE_SELL;
import static com.example.hubs.constants.OrderStatus.CANCELLED;
import static com.example.hubs.constants.OrderStatus.PENDING;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    private final ListOrderModel listOrderModel = new ListOrderModel();
    private final List<Order> orderList = new ArrayList<>();
    private final Order order = new Order();
    private final CreateOrderModel createOrderModel = new CreateOrderModel();
    private final Asset asset = new Asset();
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private AssetRepository assetRepository;
    @Spy
    private MapperServiceImpl mapper = new MapperServiceImpl();

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

        asset.setUsableSize(30L);
        asset.setId(1L);
        asset.setAssetName("test");
        asset.setCustomerId(1L);
        asset.setAssetSize(20L);

        createOrderModel.setCustomerId(1L);
        createOrderModel.setAssetName("test");
        createOrderModel.setOrderSide(ORDER_SIDE_BUY);
        createOrderModel.setPrice(10L);
        createOrderModel.setSize(2L);
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

    @Test
    void updateOrderTest_Successful() {
        when(orderRepository.findOrderByCustomerIdAndStatus(1L, PENDING.getValue())).thenReturn(Optional.of(order));
        when(assetRepository.findAssetsByModel(any())).thenReturn(List.of(asset));
        Assertions.assertDoesNotThrow(() -> orderService.update(new UpdateOrderModel(1L, CANCELLED)));
    }

    @Test
    void updateOrderTest_OrderNotFoundException() {
        when(orderRepository.findOrderByCustomerIdAndStatus(1L, PENDING.getValue())).thenReturn(Optional.empty());
        Assertions.assertThrows(CustomException.class, () -> orderService.update(new UpdateOrderModel(1L, CANCELLED)));
    }

    @Test
    void updateOrderTest_AssetNotFoundException() {
        when(orderRepository.findOrderByCustomerIdAndStatus(1L, PENDING.getValue())).thenReturn(Optional.of(order));
        when(assetRepository.findAssetsByModel(any())).thenReturn(Collections.emptyList());
        Assertions.assertThrows(CustomException.class, () -> orderService.update(new UpdateOrderModel(1L, CANCELLED)));
    }

    @Test
    void createOrderTest_Successful() {
        when(orderRepository.findOrderByCustomerIdAndStatus(1L, PENDING.getValue())).thenReturn(Optional.empty());
        when(assetRepository.findAssetsByModel(any())).thenReturn(List.of(asset));
        Assertions.assertDoesNotThrow(() -> orderService.create(createOrderModel));
    }

    @Test
    void createOrderTest_OrderFoundException() {
        when(orderRepository.findOrderByCustomerIdAndStatus(1L, PENDING.getValue())).thenReturn(Optional.of(order));
        Assertions.assertThrows(CustomException.class, () -> orderService.create(createOrderModel));
    }

    @Test
    void createOrderTest_AssetNotFoundException() {
        when(orderRepository.findOrderByCustomerIdAndStatus(1L, PENDING.getValue())).thenReturn(Optional.empty());
        when(assetRepository.findAssetsByModel(any())).thenReturn(Collections.emptyList());
        Assertions.assertThrows(CustomException.class, () -> orderService.create(createOrderModel));
    }

    @Test
    void createOrderTest_InsufficientFunds() {
        asset.setUsableSize(5L);
        when(orderRepository.findOrderByCustomerIdAndStatus(1L, PENDING.getValue())).thenReturn(Optional.empty());
        when(assetRepository.findAssetsByModel(any())).thenReturn(List.of(asset));
        Assertions.assertThrows(CustomException.class, () -> orderService.create(createOrderModel));

    }

    @Test
    void createOrderTest_OrderSideSell() {
        createOrderModel.setOrderSide(ORDER_SIDE_SELL);
        when(orderRepository.findOrderByCustomerIdAndStatus(1L, PENDING.getValue())).thenReturn(Optional.empty());
        when(assetRepository.findAssetsByModel(any())).thenReturn(List.of(asset));
        Assertions.assertDoesNotThrow(() -> orderService.create(createOrderModel));
    }
}
