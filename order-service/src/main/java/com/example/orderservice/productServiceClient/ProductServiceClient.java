package com.example.orderservice.productServiceClient;

import com.example.commonsource.productDto.ProductViewDto;
import com.example.commonsource.response.JsonResponse;
import com.example.orderservice.security.loginDto.LoginRequestDTO;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

    @PostMapping(value = "/buyProduct", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    // 포스트 요청시 client에 body가 null로 출력됨 ->  SpringQueryMap사용
    ResponseEntity<EntityModel<JsonResponse>> buyProduct(@RequestHeader(required = true) String account_token, @SpringQueryMap ProductViewDto productViewDto);
}
