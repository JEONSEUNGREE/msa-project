package com.example.eurekaserver.configController;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JsonResponse {
    String localServerPort;
    String tokenSecret;
    String tokenExpirationTime;
    String gatewayIp;
}