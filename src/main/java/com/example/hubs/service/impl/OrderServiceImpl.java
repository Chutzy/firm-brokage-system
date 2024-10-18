package com.example.hubs.service.impl;

import com.example.hubs.entity.Asset;
import com.example.hubs.entity.Order;
import com.example.hubs.exception.CustomException;
import com.example.hubs.infra.MapperService;
import com.example.hubs.model.*;
import com.example.hubs.repository.AssetRepository;
import com.example.hubs.repository.OrderRepository;
import com.example.hubs.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.hubs.constants.Constants.OrderConstants.ORDER_SIDE_BUY;
import static com.example.hubs.constants.OrderStatus.CANCELLED;
import static com.example.hubs.constants.OrderStatus.PENDING;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AssetRepository assetRepository;
    private final MapperService mapper;

    @Override
    public ListOrderResponseModel list(ListOrderModel model) {
        List<Order> orderList = orderRepository.findOrdersByCriteria(model);
        if (orderList.isEmpty()) {
            throw new CustomException("order list is empty");
        }
        ListOrderResponseModel listOrderResponseModel = new ListOrderResponseModel();
        listOrderResponseModel.setOrderList(orderList);
        return listOrderResponseModel;
    }

    @Transactional
    @Override
    public void create(CreateOrderModel model) {
        orderRepository.findOrderByCustomerIdAndStatus(model.getCustomerId(), PENDING.getValue())
                .ifPresent(o -> {
                    throw new CustomException("there is a pending order for customer");
                });
        Asset asset = assetRepository.findAssetsByModel(new ListAssetModel(model.getCustomerId(), model.getAssetName(), null, null))
                .stream().findFirst().orElse(null);
        createOrderValidations(asset, model);
        calculateCreateOrder(asset, model);
        saveNewOrder(model);
    }

    @Transactional
    @Override
    public void update(UpdateOrderModel model) {
        Order order = orderRepository.findOrderByCustomerIdAndStatus(model.getCustomerId(), PENDING.getValue()).orElseThrow(() -> new CustomException("order not found"));
        Asset asset = assetRepository.findAssetsByModel(new ListAssetModel(model.getCustomerId(), null, null, null)).stream().findFirst().orElseThrow(() -> new CustomException("no assets found"));
        if (model.getOrderStatus().equals(CANCELLED)) {
            calculateCancelOrder(asset, order);
        }
        order.setStatus(CANCELLED.getValue());
        order.setUpdateTime(LocalDateTime.now());
        orderRepository.save(order);
    }

    private void calculateCancelOrder(Asset asset, Order order) {
        asset.setUsableSize(asset.getUsableSize() + order.getSize() * order.getPrice());
        assetRepository.save(asset);
    }

    private void calculateCreateOrder(Asset asset, CreateOrderModel model) {
        if (ORDER_SIDE_BUY.equals(model.getOrderSide())) {
            asset.setUsableSize(asset.getUsableSize() - model.getSize() * model.getPrice());
        } else {
            asset.setUsableSize(asset.getUsableSize() + model.getSize() * model.getPrice());
        }
        assetRepository.save(asset);
    }

    private void createOrderValidations(Asset asset, CreateOrderModel model) {
        if (asset == null) {
            throw new CustomException("asset not found");
        }
        if (ORDER_SIDE_BUY.equals(model.getOrderSide()) && asset.getUsableSize() < model.getSize() * model.getPrice()) {
            throw new CustomException("insufficient funds");
        }
    }

    private void saveNewOrder(CreateOrderModel model) {
        Order order = mapper.map(model, Order.class);
        order.setStatus(PENDING.getValue());
        order.setCreateTime(LocalDateTime.now());
        orderRepository.save(order);
    }
}
