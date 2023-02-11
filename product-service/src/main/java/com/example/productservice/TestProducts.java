package com.example.productservice;

import com.example.productservice.product.entity.ProductsMs;
import com.example.productservice.product.repository.ProductsMsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TestProducts implements CommandLineRunner {

    private final ProductsMsRepository repository;

    @Override
    public void run(String... args) throws Exception {
        List<String> albumList = Arrays.asList("justin bieber1", "justin bieber2", "taylor swift1", "taylor swift2"
                , "taylor swift3", "taylor swift4", "chrisBrown1", "chrisBrown2", "bigBang1", "bigBang2");
        for (int i = 1; i <= 10; i++) {
            ProductsMs productMs = ProductsMs.builder()
                    .productLocation("seoul")
                    .productDesc("dsec")
                    .productName(albumList.get(i - 1))
                    .productPrice(10000)
                    .categoryId(1)
                    .userId("test")
                    .qty(50)
                    /* 썸네일 이미지 url /product-image/** */
                    .productImages("/product-service/product-image/" + i + ".jpg")
                    .modifyDate(new Date())
                    .regDate(new Date())
                    .productStatus("RES")
                    .build();
            repository.save(productMs);
        }
    }
}
