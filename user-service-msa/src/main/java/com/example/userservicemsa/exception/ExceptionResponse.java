package com.example.userservicemsa.exception;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
public class ExceptionResponse {
    private String message;
    private String details;
    private Date timestamp;
    private String errorCode;
}
