package com.example.userservicemsa.interceptor;

import com.example.userservicemsa.interceptor.annotation.CurrentUser;
import com.example.userservicemsa.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


@Component
@RequiredArgsConstructor
public class CommonArgumentResolver implements HandlerMethodArgumentResolver {

    private final MemberService memberService;


    // 어노테이션 존재 여부 확인
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class);
    }

    // 실제 바인딩할 객체를 반환한다.
    @Override
    public Object resolveArgument(@NotNull MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  @NotNull NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
