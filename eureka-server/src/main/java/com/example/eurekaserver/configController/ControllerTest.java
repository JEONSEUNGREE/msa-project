package com.example.eurekaserver.configController;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ControllerTest {

    private final Environment env;

    public ControllerTest(Environment env) {
        this.env = env;
    }

    @GetMapping(value = "/serverProperties")
    public ResponseEntity<JsonResponse> serverConfigInfo() {
        JsonResponse response = JsonResponse.builder()
                .localServerPort(env.getProperty("local.server.port"))
                .tokenSecret(env.getProperty("token.secret"))
                .tokenExpirationTime(env.getProperty("token.expiration_time"))
                .gatewayIp(env.getProperty("gateway.ip"))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
