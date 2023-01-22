package com.example.userservicemsa.client;


import com.example.commonsource.response.JsonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "order-service")
public interface OrderServiceClient {

    @GetMapping(value = "/orderList")
    EntityModel<JsonResponse> getOrderList(@RequestHeader(value = "account_token", required = true) String account_token);
}
