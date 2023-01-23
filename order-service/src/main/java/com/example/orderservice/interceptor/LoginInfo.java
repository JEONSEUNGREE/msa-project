package com.example.orderservice.interceptor;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginInfo {

    public String userId;

    public String jwtToken;

}
