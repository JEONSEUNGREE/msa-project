package com.example.orderservice.kafka;


import com.example.commonsource.constant.CommonKafka;
import com.example.commonsource.productDto.ProductViewDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KafkaConsumer {


    private final String rollbackTopic = "order-rollback";

    @KafkaListener(topics = rollbackTopic)
    public void rollbackOrder(String rollbackOrder) {
        log.info("Rollback Order: ->" + rollbackOrder);

        ObjectMapper mapper = new ObjectMapper();
        try {
            ProductViewDto productViewDto = mapper.readValue(rollbackOrder, ProductViewDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
