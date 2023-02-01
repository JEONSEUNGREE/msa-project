package com.example.commonsource.constant;

public enum CommonKafka {

    KAFKA_ORDER_CREATE_TOPIC("order-create"),
    KAFKA_ORDER_ROLLBACK_TOPIC("order-rollback"),
    KAFKA_PRODUCT_CREATE_TOPIC("product-create"),
    KAFKA_PRODUCT_ROLLBACK_TOPIC("product-rollback")
    ;

    private final String topic;

    CommonKafka(String topic) {
        this.topic = topic;
    }

    public String topic() {
        return topic;
    }
}
