package com.example.orderservice.order.service;


import com.example.commonsource.constant.CommonOrderStatus;
import com.example.commonsource.exception.NoOrderResult;
import com.example.commonsource.exception.OrderProcessingException;
import com.example.commonsource.orderDto.OrderResultDto;
import com.example.commonsource.orderDto.OrderViewDto;
import com.example.commonsource.productDto.ProductViewDto;
import com.example.orderservice.interceptor.LoginInfo;
import com.example.orderservice.kafka.KafkaProducer;
import com.example.orderservice.order.orderEntity.OrderMs;
import com.example.orderservice.order.repository.OrderMsRepository;
import com.example.orderservice.productServiceClient.ProductServiceClient;
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

    private final ProductServiceClient productServiceClient;

    private final KafkaProducer kafkaProducer;

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
    public void orderProduct(OrderViewDto orderViewDto, LoginInfo loginInfo) {
        try {
//            Optional<Integer> maxSequence = orderMsRepository.getMaxSequence(loginInfo.getUserId());
//
//            Integer sequence = maxSequence.orElseGet(() -> 1);

            OrderMs orderMs = OrderMs.builder()
                    .userId(loginInfo.getUserId())
                    .product_id(orderViewDto.getProductId())
                    .category_id(orderViewDto.getCategoryId())
                    .product_name(orderViewDto.getProductName())
                    .order_sequence(1)
                    .order_status(CommonOrderStatus.ORDER_STATUS_10.label())
                    .qty(orderViewDto.getQty())
                    .regDate(new Date())
                    .modifyDate(new Date())
                    .build();

            OrderMs orderSaved = orderMsRepository.save(orderMs);

            ProductViewDto productDto = ProductViewDto.builder()
                    .productId(orderViewDto.getProductId())
                    .orderId(orderSaved.getOrderId())
                    .qty(orderViewDto.getQty())
                    .build();

            /**
             * 롤백시 대상
             * @see com.example.orderservice.kafka.KafkaConsumer
             */
            kafkaProducer.orderToProduct(productDto);

        } catch (Exception e) {
            throw new OrderProcessingException("ORDER ERROR");
        }
    }
}
