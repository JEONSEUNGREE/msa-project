package com.example.commonsource.response;


import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class ExceptionResponse {
    private String message;
    private String details;
    private Date timestamp;
    private String errorCode;
}
