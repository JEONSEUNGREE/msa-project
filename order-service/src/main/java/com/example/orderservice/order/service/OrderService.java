package com.example.orderservice.order.service;

import com.example.commonsource.orderDto.OrderResultDto;
import com.example.commonsource.orderDto.OrderViewDto;
import com.example.orderservice.interceptor.LoginInfo;

import java.util.List;

public interface OrderService {

    public List<OrderResultDto> getOneUserOrderList(String userId);

    public void orderProduct(OrderViewDto orderViewDto, LoginInfo loginInfo);
}
