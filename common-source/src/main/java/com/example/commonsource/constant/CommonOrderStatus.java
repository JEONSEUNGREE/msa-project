package com.example.commonsource.constant;


public enum CommonOrderStatus {

    ORDER_STATUS_10("waiting for payment"),
    ORDER_STATUS_20("cancel"),
    ORDER_STATUS_30(""),
    ORDER_STATUS_40("payment confirmation"),
    ORDER_STATUS_50("shipping"),
    ORDER_STATUS_60("Delivery completed")
    ;

    private final String label;

    CommonOrderStatus(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

}
