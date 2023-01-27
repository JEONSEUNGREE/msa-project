package com.example.orderservice.order.service;


import com.example.commonsource.constant.CommonOrderStatus;
import com.example.commonsource.exception.NoOrderResult;
import com.example.commonsource.exception.OrderProcessingException;
import com.example.commonsource.orderDto.OrderResultDto;
import com.example.commonsource.orderDto.OrderViewDto;
import com.example.orderservice.order.orderEntity.OrderMs;
import com.example.orderservice.order.repository.OrderMsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderMsRepository orderMsRepository;


    @Override
    public List<OrderResultDto> getOneUserOrderList(String userId) {

        Optional<List<OrderMs>> userOrderList = orderMsRepository.findAllByUserId(userId);

        userOrderList.orElseThrow(() -> new NoOrderResult("주문 내역이 없습니다."));

        return userOrderList.get().stream().map(item ->
                OrderResultDto.builder()
                        .productName(item.getProduct_name())
                        .orderDate(item.getRegDate())
                        .orderStatus(item.getOrder_status())
                        .qty(item.getQty())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public void orderProduct(OrderViewDto orderViewDto, String userId) {
        try {
            Optional<Integer> maxSequence = orderMsRepository.getMaxSequence(userId);

            Integer sequence = maxSequence.orElseGet(() -> 1);

            OrderMs orderMs = OrderMs.builder()
                    .userId(userId)
                    .category_id(orderViewDto.getCategoryId())
                    .product_name(orderViewDto.getProductName())
                    .order_sequence(sequence)
                    .order_status(CommonOrderStatus.ORDER_STATUS_10.label())
                    .qty(orderViewDto.getQty())
                    .regDate(new Date())
                    .modifyDate(new Date())
                    .build();

            orderMsRepository.save(orderMs);
        } catch (Exception e) {
            throw new OrderProcessingException("ORDER ERROR");
        }
    }
}
