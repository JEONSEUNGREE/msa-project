package com.example.orderservice.security.filter;


import com.example.orderservice.security.securityUtil.CookieUtil;
import com.example.orderservice.security.securityUtil.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String token = null;

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;

        Cookie accountTokenCookie = cookieUtil.getCookie(req, "account_token");
        if (accountTokenCookie != null) {
            token = accountTokenCookie.getValue();
        }

        if (token == null) {
            token = req.getHeader("account_token");
        }

        if(token != null && !jwtUtil.isTokenExpired(token)) {
            try {
                String userId = jwtUtil.getUserIdFromToken(token);

                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, "account_token"));
            } catch(Exception e) {
                onError(req, res);
            }
        }

        chain.doFilter(request, response);
    }

    private void onError(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        JSONObject resJson = new JSONObject();
        resJson.put("code", 401);
        resJson.put("message", "LOGIN PLEASE");

        res.getWriter().write(resJson.toString());
    }
}