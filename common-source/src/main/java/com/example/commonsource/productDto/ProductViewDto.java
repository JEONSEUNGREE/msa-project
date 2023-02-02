package com.example.commonsource.productDto;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductViewDto {

    private Integer productId;
    private Integer orderId;
    private Integer qty;
}
