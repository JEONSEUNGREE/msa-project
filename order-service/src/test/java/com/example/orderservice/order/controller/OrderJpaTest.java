//package com.example.orderservice.order.controller;
//
//import com.example.commonsource.constant.CommonOrderStatus;
//import com.example.commonsource.orderDto.OrderResultDto;
//import com.example.commonsource.orderDto.OrderViewDto;
//import com.example.orderservice.interceptor.LoginInfo;
//import com.example.orderservice.order.service.OrderService;
//import com.example.orderservice.order.util.UserUtil;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.elasticsearch.AutoConfigureDataElasticsearch;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
////@AutoConfigureDataElasticsearch
//public class OrderJpaTest {
//
//
//    @Autowired
//    private OrderService orderService;
//
//    @Autowired
//    private UserUtil userUtil;
//
//    @Test
//    public void orderProducts() {
//        /* given */
//        LoginInfo loginInfo = LoginInfo.builder()
//                .userId("TEST_USER")
//                .jwtToken("TEST_JWT_TOKEN")
//                .build();
//
//        OrderViewDto testProducts = OrderViewDto.builder()
//                .productName("TEST_PRODUCTS")
//                .categoryId("50")
//                .qty(1)
//                .build();
//
//        /* when */
//        orderService.orderProduct(testProducts, loginInfo.getUserId());
//
//        List<OrderResultDto> userOrderList = orderService.getOneUserOrderList(loginInfo.getUserId());
//
//        OrderResultDto orderResultDto = userOrderList.stream()
//                .filter(item -> "TEST_PRODUCTS".equals(item.getProductName()))
//                .findAny().orElse(OrderResultDto.builder().build());
//
//        /* then */
//        assertThat(orderResultDto.getProductName()).isEqualTo(testProducts.getProductName());
//        assertThat(orderResultDto.getQty()).isEqualTo(testProducts.getQty());
//        assertThat(orderResultDto.getOrderStatus()).isEqualTo(CommonOrderStatus.ORDER_STATUS_10.label());
//    }
//}
