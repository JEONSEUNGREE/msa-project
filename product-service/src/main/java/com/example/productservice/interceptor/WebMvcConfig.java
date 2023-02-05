package com.example.productservice.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${file.request.path}")
    private String localResourcePath;


    @Value("${file.resource.path}")
    private String productStaticPath;

    private final CommonInterceptor commonInterceptor;

    private final CommonArgumentResolver commonArgumentResolver;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // 다음 요청에 대한 매핑 /pImage/**
        registry.addResourceHandler(productStaticPath)
                // 위 요청에 대한 서버 정적파일 위치
                .addResourceLocations(localResourcePath)
                .setCachePeriod(20);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(commonArgumentResolver);
    }

}
