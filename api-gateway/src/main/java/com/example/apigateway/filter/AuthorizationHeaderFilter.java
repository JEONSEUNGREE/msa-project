package com.example.apigateway.filter;


import com.example.apigateway.SecutiryUtil.CookieUtil;
import com.example.apigateway.SecutiryUtil.JwtUtil;
import io.netty.handler.codec.http.cookie.Cookie;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    private final Environment env;

    private final CookieUtil cookieUtil;

    private final JwtUtil jwtUtil;

    public static class Config {
    }
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            String token = null;

            // 쿠키 체크
            Cookie accountTokenCookie = cookieUtil.getCookie(request, "account_token");

            if (accountTokenCookie != null) {
                token = accountTokenCookie.value();
            }

            // 헤더 체크
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "NO AUTHORIZATION HEADER", HttpStatus.UNAUTHORIZED);
            }

            if (token != null && !jwtUtil.isTokenExpired(token)) {

                String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

                token = authorizationHeader.replace("Bearer ", "");

                jwtUtil.validateToken(token);
            }

            return chain.filter(exchange);
        });
    }

    private Mono<Void> onError(ServerWebExchange exchange, String error, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);

        return response.setComplete();
    }
}


