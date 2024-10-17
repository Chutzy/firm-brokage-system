package com.example.hubs.model;

import com.example.hubs.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ListOrderResponseModel {

    List<Order> orderList = new ArrayList<>();
}
