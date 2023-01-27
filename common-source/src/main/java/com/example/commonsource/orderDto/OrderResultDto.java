package com.example.commonsource.orderDto;


import com.example.commonsource.categoryDto.CategoryDto;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class OrderResultDto {

    public String productName;
    public Date orderDate;
    public String orderStatus;
    public int qty;

}
