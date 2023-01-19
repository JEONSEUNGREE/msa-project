package com.example.commonsource.response;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PropertiesResponse {
    String localServerPort;
    String tokenSecret;
    String tokenExpirationTime;
    String gatewayIp;
}