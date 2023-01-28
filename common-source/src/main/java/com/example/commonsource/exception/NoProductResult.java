package com.example.commonsource.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoProductResult extends RuntimeException{

    public NoProductResult(String message) {
        super(message);
    }
}
