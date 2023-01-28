package com.example.commonsource.productDto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductResultDto {

    private Integer productId;
    private String productName;
    private Integer productPrice;
    private String status;
    private String productLocation;
    private Integer qty;

}
