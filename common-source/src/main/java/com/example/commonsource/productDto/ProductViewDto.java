package com.example.commonsource.productDto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductViewDto {

    private Integer productId;
    private Integer qty;
}
