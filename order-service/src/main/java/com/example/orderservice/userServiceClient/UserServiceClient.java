package com.example.orderservice.userServiceClient;

import com.example.orderservice.security.loginDto.LoginRequestDTO;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "user-service", url = "${domain.api.user-service}")
public interface UserServiceClient {

    @PostMapping(value = "/login", headers = "X-API-VERSION=1")
    public Response loginVersion1(@RequestBody(required = true) LoginRequestDTO loginRequestDTO);

}
