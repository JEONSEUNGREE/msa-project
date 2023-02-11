package com.example.productservice.product.service;

import com.example.commonsource.exception.NoProductResult;
import com.example.commonsource.productDto.ProductResultDto;
import com.example.commonsource.productDto.ProductViewDto;
import com.example.productservice.kafka.KafkaProducer;
import com.example.productservice.product.entity.ProductsMs;
import com.example.productservice.product.repository.ProductsMsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductsMsRepository productsMsRepository;

    private final KafkaProducer kafkaProducer;

    @Override
    public ProductResultDto getProductInfo(Integer productId) {

        Optional<ProductsMs> productsMs = productsMsRepository.findById(productId);

        productsMs.orElseThrow(() -> new NoProductResult("존재하지않는 상품입니다."));

        return ProductResultDto.builder()
                .productId(productsMs.get().getProductId())
                .productName(productsMs.get().getProductName())
                .productPrice(productsMs.get().getProductPrice())
                .qty(productsMs.get().getQty())
                .productLocation(productsMs.get().getProductLocation())
                .build();
    }

    @Override
    public void buyProduct(String userId, ProductViewDto productViewDto) {
        try {
            Optional<ProductsMs> productsMs = productsMsRepository.findById(productViewDto.getProductId());
            throw new NoProductResult("존재하지않는 상품입니다.");
//            productsMs.orElseThrow(() -> new NoProductResult("존재하지않는 상품입니다."));
//
//            productsMs.get().changeProductQty(productViewDto.getQty(), "buy");
//
//            productsMsRepository.save(productsMs.get());
        } catch (NoProductResult ex) {
            kafkaProducer.rollbackOrder(productViewDto);
        }
    }

    @Override
    public void cancelProduct(String userId, ProductViewDto productViewDto) {
        try {
            Optional<ProductsMs> productsMs = productsMsRepository.findById(productViewDto.getProductId());

            productsMs.orElseThrow(() -> new NoProductResult("존재하지않는 상품입니다."));

            productsMs.get().changeProductQty(productViewDto.getQty(), "cancel");

            productsMsRepository.save(productsMs.get());
        } catch (NoProductResult ex) {
            throw new NoProductResult("존재하지않는 상품입니다.");
        }
    }

    @Override
    public List<ProductResultDto> sellProductList() {
        List<ProductsMs> productList = productsMsRepository.findAll();
        return productList.stream().map(item ->
                ProductResultDto.builder()
                        .productId(item.getProductId())
                        .productName(item.getProductName())
                        .qty(item.getQty())
                        .productThumbnail(item.getProductImages())
                        .status(item.getProductStatus())
                        .productPrice(item.getProductPrice())
                        .build()).collect(Collectors.toList());
    }
}
