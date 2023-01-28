package com.example.productservice.product.repository;

import com.example.productservice.product.entity.ProductsMs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductsMsRepository extends JpaRepository<ProductsMs, Integer> {

}