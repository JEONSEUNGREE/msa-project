package com.example.eurekaserver;


import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/test")
    public ResponseEntity<JsonResponse> serverConfigInfo() {
        JsonResponse response = JsonResponse.builder()
                .localServerPort(env.getProperty("local.server.port"))
                .localServerPort(env.getProperty("token.port"))
                .localServerPort(env.getProperty("token.secret"))
                .localServerPort(env.getProperty("token.expiration_time"))
                .localServerPort(env.getProperty("gateway.ip"))
                .build();
        return ResponseEntity.ok(response);
    }

    @Builder
    static class JsonResponse {
        private String localServerPort;
        private String tokenSecret;
        private String tokenExpirationTime;
        private String gatewayIp;
    }
}
