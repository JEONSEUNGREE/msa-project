package com.example.productservice.product.controller;


import com.example.commonsource.productDto.ProductResultDto;
import com.example.commonsource.productDto.ProductViewDto;
import com.example.commonsource.response.JsonResponse;
import com.example.productservice.interceptor.LoginInfo;
import com.example.productservice.interceptor.annotation.CurrentUser;
import com.example.productservice.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/getProductInfo")
    public ResponseEntity<EntityModel<JsonResponse>> getProductInfo(@RequestParam(value = "productId") Integer productId) {

        ProductResultDto productInfo = productService.getProductInfo(productId);

        JsonResponse success = JsonResponse.builder()
                .status(HttpStatus.OK)
                .msg("SUCCESS")
                .responseData(productInfo)
                .build();


        EntityModel<JsonResponse> response = EntityModel.of(success);

        response.add(linkTo(methodOn(ProductController.class).getProductInfo(productId)).withSelfRel());
        response.add(linkTo(methodOn(ProductController.class).buyProduct(LoginInfo.builder().build(), ProductViewDto.builder().build())).withRel("buyProduct"));
        response.add(linkTo(methodOn(ProductController.class).cancelProduct(LoginInfo.builder().build(), ProductViewDto.builder().build())).withRel("cancelProduct"));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/buyProduct")
    public ResponseEntity<EntityModel<JsonResponse>> buyProduct(@CurrentUser LoginInfo loginInfo, ProductViewDto productViewDto) {

        productService.buyProduct(loginInfo.getUserId(), productViewDto);

        JsonResponse success = JsonResponse.builder()
                .status(HttpStatus.OK)
                .msg("SUCCESS")
                .build();

        EntityModel<JsonResponse> response = EntityModel.of(success);

        response.add(linkTo(methodOn(ProductController.class).buyProduct(loginInfo, productViewDto)).withSelfRel());
        response.add(linkTo(methodOn(ProductController.class).getProductInfo(productViewDto.getProductId())).withRel("getProductInfo"));
        response.add(linkTo(methodOn(ProductController.class).cancelProduct(LoginInfo.builder().build(), ProductViewDto.builder().build())).withRel("cancelProduct"));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }


    @PutMapping(value = "/cancelProduct")
    public ResponseEntity<EntityModel<JsonResponse>> cancelProduct(@CurrentUser LoginInfo loginInfo, ProductViewDto productViewDto) {

        productService.cancelProduct(loginInfo.getUserId(), productViewDto);

        JsonResponse success = JsonResponse.builder()
                .status(HttpStatus.OK)
                .msg("SUCCESS")
                .build();

        EntityModel<JsonResponse> response = EntityModel.of(success);

        response.add(linkTo(methodOn(ProductController.class).cancelProduct(loginInfo, productViewDto)).withSelfRel());
        response.add(linkTo(methodOn(ProductController.class).buyProduct(LoginInfo.builder().build(), ProductViewDto.builder().build())).withRel("buyProduct"));
        response.add(linkTo(methodOn(ProductController.class).getProductInfo(productViewDto.getProductId())).withRel("getProductInfo"));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
