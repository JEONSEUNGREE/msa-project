package com.example.productservice.product.repository;

import com.example.productservice.product.entity.ProductsImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductsImgRepository extends JpaRepository<ProductsImg, Integer> {

}