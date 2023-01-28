package com.example.productservice.product.service;

import com.example.commonsource.productDto.ProductResultDto;
import com.example.commonsource.productDto.ProductViewDto;

public interface ProductService {

    public ProductResultDto getProductInfo(Integer productId);

    public void buyProduct(String userId, ProductViewDto productViewDto);

    public void cancelProduct(String userId, ProductViewDto productViewDto);
}
