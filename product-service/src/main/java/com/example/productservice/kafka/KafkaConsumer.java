package com.example.productservice.kafka;

import com.example.commonsource.constant.CommonKafka;
import com.example.commonsource.productDto.ProductViewDto;
import com.example.productservice.product.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final ProductService productService;


    @KafkaListener(topics = CommonKafka.KAFKA_ORDER_CREATE_TOPIC_VALUE, groupId = CommonKafka.KAFKA_GROUP_ID)
    public void createProduct(String productInfo) {
        log.info("Create Product: ->" + productInfo);

        ObjectMapper mapper = new ObjectMapper();
        ProductViewDto createProduct = null;

        try {
            createProduct = mapper.readValue(productInfo, ProductViewDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        productService.buyProduct("tempUser", createProduct);
    }
}
