package com.example.commonsource.orderDto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class OrderViewDto {

    /* 상품 아이디 */
    public Integer productId;
    /* 카테고리 아이디 */
    public String categoryId;
    /* 상품 이름 */
    public String productName;
    /* 수량 */
    public int qty;

}
