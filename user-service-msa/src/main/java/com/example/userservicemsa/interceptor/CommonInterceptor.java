package com.example.userservicemsa.interceptor;

import com.example.commonsource.exception.ApiException;
import com.example.commonsource.exception.UserNotFoundException;
import com.example.userservicemsa.interceptor.annotation.LoginCheck;
import com.example.userservicemsa.interceptor.annotation.VersionCheck;
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
        String version = null;

        /* 로그인 여부 */
        LoginCheck loginCheck = handlerMethod.getMethodAnnotation(LoginCheck.class);

        /* 버전 체크 */
        VersionCheck versionCheck = handlerMethod.getMethodAnnotation(VersionCheck.class);


        if (loginCheck != null && SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            throw new UserNotFoundException("LOGIN PLEASE");
        }

        if (versionCheck == null) {
            return true;
        }

        version = request.getHeader(versionCheck.versionKey());

        if (version == null) {
            throw new com.example.commonsource.exception.ApiException("API VERSION IS REQUIRED");
        }

        if (!version.equals(versionCheck.versionValue())) {
            throw new ApiException("API VERSION IS INVALID");
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
