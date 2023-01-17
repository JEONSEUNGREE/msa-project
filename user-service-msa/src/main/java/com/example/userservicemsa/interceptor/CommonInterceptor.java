package com.example.userservicemsa.interceptor;

import com.example.userservicemsa.exception.UserNotFoundException;
import com.example.userservicemsa.interceptor.annotation.LoginCheck;
import io.micrometer.core.ipc.http.HttpSender;
import org.hibernate.validator.internal.util.privilegedactions.GetMethod;
import org.jetbrains.annotations.NotNull;
import org.springframework.messaging.handler.HandlerMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CommonInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {

//        if( !( handler instanceof HandlerMethod ) ) {
//            return true;
//        }

        // 어노테이션 타입 확인
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginCheck loginCheck = handlerMethod.getMethodAnnotation(LoginCheck.class);

        if (loginCheck == null) {
            return true;
        }

        if (SecurityContextHolder.getContext() == null) {
            throw new UserNotFoundException("LOGIN PLEASE");
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
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
