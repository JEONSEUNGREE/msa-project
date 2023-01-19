package com.example.userservicemsa.client;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "order-service")
public interface OrderServiceClient {

//    getOrders
}
