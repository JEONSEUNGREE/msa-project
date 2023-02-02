package com.example.orderservice.kafka;

import com.example.commonsource.constant.CommonKafka;
import com.example.commonsource.productDto.ProductViewDto;
import com.example.orderservice.order.repository.OrderMsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    private OrderMsRepository orderMsRepository;

    @Autowired
    public KafkaConsumer(OrderMsRepository orderMsRepository) {
        this.orderMsRepository = orderMsRepository;
    }

    @KafkaListener(topics = CommonKafka.KAFKA_ORDER_ROLLBACK_TOPIC_VALUE, groupId = CommonKafka.KAFKA_GROUP_ID)
    public void rollbackOrder(String rollbackOrder) {
        log.info("Rollback Order: ->" + rollbackOrder);

        ObjectMapper mapper = new ObjectMapper();
        ProductViewDto productViewDto = null;

        try {
            productViewDto = mapper.readValue(rollbackOrder, ProductViewDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        orderMsRepository.deleteById(productViewDto.getOrderId());
    }
}
