package com.example.userservicemsa.servletutil;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Builder
@Getter
public class JsonResponse {
    HttpStatus status;
    String msg;
    Object responseData;
}
