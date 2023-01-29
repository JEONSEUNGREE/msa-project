package com.example.commonsource.constant;


public enum CommonOrderStatus {

    ORDER_STATUS_10("wait"),
    ORDER_STATUS_20("cancel"),
    ORDER_STATUS_30(""),
    ORDER_STATUS_40("paymentCh"),
    ORDER_STATUS_50("shipping"),
    ORDER_STATUS_60("DeliverCom")
    ;

    private final String label;

    CommonOrderStatus(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

}
