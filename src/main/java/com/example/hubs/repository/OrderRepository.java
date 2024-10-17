package com.example.hubs.repository;

import com.example.hubs.entity.Order;
import com.example.hubs.model.ListOrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT o FROM Order o where " +
            "(o.customerId = :#{#criteria.customerId})" +
            "and (o.assetName is null or o.assetName = :#{#criteria.assetName})" +
            "and (o.orderSide is null or o.orderSide = :#{#criteria.orderSide})" +
            "and (o.size is null or o.size = :#{#criteria.size})" +
            "and (o.price is null or o.price = :#{#criteria.price})" +
            "and (o.status is null or o.status = :#{#criteria.status})" +
            "and o.createDate between :#{#criteria.startDate} and :#{#criteria.endDate}")
    List<Order> findOrdersByCriteria(ListOrderModel criteria);

    //Optional<Order> findOrderByIdAndStatus(Long id, String status);

    @Modifying
    @Query(value = "UPDATE Order o set o.status = :#{#status} where o.id = :#{#orderId}")
    void updatePendingOrderByOrderIdAndStatus(Long orderId, String status);
}
