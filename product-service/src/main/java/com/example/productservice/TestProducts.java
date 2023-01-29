package com.example.productservice;

import com.example.productservice.product.entity.ProductsMs;
import com.example.productservice.product.repository.ProductsMsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class TestProducts implements CommandLineRunner {


    private final ProductsMsRepository repository;

    @Override
    public void run(String... args) throws Exception {

            ProductsMs productMs = ProductsMs.builder()
                    .productId(1)
                    .productLocation("seoul")
                    .productDesc("dsec")
                    .productName("phone")
                    .productPrice(10000)
                    .categoryId(1)
                    .userId("test")
                    .qty(50)
                    .modifyDate(new Date())
                    .regDate(new Date())
                    .productStatus("RES")
                    .build();
            repository.save(productMs);
    }
}
