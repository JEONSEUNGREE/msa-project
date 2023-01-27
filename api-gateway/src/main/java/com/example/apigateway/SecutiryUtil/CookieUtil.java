package com.example.apigateway.SecutiryUtil;

import io.netty.handler.codec.http.cookie.Cookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {
    public Cookie getCookie(ServerHttpRequest req, String account_token) {
        return null;
    }
}
