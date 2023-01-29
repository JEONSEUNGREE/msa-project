package com.example.commonsource.productDto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductViewDto {

    private Integer productId;
    private Integer qty;
}
