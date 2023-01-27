package com.example.orderservice.order.service;

import com.example.commonsource.orderDto.OrderResultDto;
import com.example.commonsource.orderDto.OrderViewDto;

import java.util.List;

public interface OrderService {

    public List<OrderResultDto> getOneUserOrderList(String userId);

    public void orderProduct(OrderViewDto orderViewDto, String userId);
}
