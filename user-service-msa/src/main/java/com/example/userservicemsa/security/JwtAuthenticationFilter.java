package com.example.userservicemsa.security;


import com.example.userservicemsa.securityUtil.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;
    private final CookieUtil cookieUtil;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String token = null;
        Authentication authenticate;

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;

        Cookie accountTokenCookie = cookieUtil.getCookie(req, "account_token");
        if (accountTokenCookie != null) {
            token = accountTokenCookie.getValue();
        }

        if (token == null) {
            token = req.getHeader("authentication");
        }

        if(token != null && !jwtProvider.isTokenExpired(token)) {
            try {
                String userId = jwtProvider.getUserIdFromToken(token);
                authenticate = jwtProvider.authenticate(new UsernamePasswordAuthenticationToken(userId, ""));
                SecurityContextHolder.getContext().setAuthentication(authenticate);
                chain.doFilter(request, response);
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