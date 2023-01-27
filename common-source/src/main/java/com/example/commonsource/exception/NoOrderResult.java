package com.example.commonsource.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoOrderResult extends RuntimeException{

    public NoOrderResult(String message) {
        super(message);
    }
}
