package com.example.userservicemsa.interceptor;

import com.example.userservicemsa.exception.UserNotFoundException;
import com.example.userservicemsa.interceptor.annotation.LoginCheck;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CommonInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {

        if(!( handler instanceof HandlerMethod )) {
            return true;
        }
        // 어노테이션 타입 확인
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginCheck loginCheck = handlerMethod.getMethodAnnotation(LoginCheck.class);

        if (loginCheck == null) {
            return true;
        }

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            throw new UserNotFoundException("LOGIN PLEASE");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
