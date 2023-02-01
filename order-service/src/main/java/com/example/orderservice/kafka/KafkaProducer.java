package com.example.orderservice.kafka;

import com.example.commonsource.constant.CommonKafka;
import com.example.commonsource.productDto.ProductViewDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void orderToProduct(ProductViewDto productViewDto) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        // 카프카 메시지 전달시 역직렬화 필요
        try {
            jsonInString = mapper.writeValueAsString(productViewDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        kafkaTemplate.send(CommonKafka.KAFKA_ORDER_CREATE_TOPIC.topic(), jsonInString);
    }

}
