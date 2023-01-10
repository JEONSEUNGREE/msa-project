package com.example.userservicemsa.security;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class CookieUtil {
    public Cookie getCookie(HttpServletRequest req, String account_token) {
        return null;
    }
}
