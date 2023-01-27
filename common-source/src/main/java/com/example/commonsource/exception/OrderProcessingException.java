package com.example.commonsource.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderProcessingException extends RuntimeException{
    public OrderProcessingException(String msg) {
        super(msg);
    }
}
