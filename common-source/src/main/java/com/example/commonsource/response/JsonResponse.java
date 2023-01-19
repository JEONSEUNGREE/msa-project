package com.example.commonsource.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Builder
@Getter
public class JsonResponse {
    HttpStatus status;
    String msg;
    Object responseData;
}
