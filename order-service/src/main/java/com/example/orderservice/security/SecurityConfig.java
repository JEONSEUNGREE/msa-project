package com.example.orderservice.security;

import com.example.commonsource.response.ExceptionResponse;
import com.example.orderservice.constants.SecurityConstant;
import com.example.orderservice.security.filter.JwtAuthenticationFilter;
import com.example.orderservice.security.securityUtil.CookieUtil;
import com.example.orderservice.security.securityUtil.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Date;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final ObjectMapper objectMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    JwtAuthenticationFilter jwtAuthenticationFilter(JwtUtil jwtProvider, CookieUtil cookieUtil) {
        return new JwtAuthenticationFilter(jwtProvider, cookieUtil);
    }
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring().mvcMatchers(SecurityConstant.resourceArray);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtUtil jwtProvider, CookieUtil cookieUtil) throws Exception {


        http.httpBasic().disable()
                .formLogin().disable()
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.authorizeRequests()
                .antMatchers(SecurityConstant.permitAllArray).permitAll()
                .antMatchers(SecurityConstant.authenticationAllArray).authenticated()
                .and()
                .addFilterAt(jwtAuthenticationFilter(jwtProvider, cookieUtil), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(((request, response, authException) -> {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    objectMapper.writeValue(
                            response.getOutputStream(),
                            ExceptionResponse.builder()
                                    .message("FAIL")
                                    .details("UNKNOWN USER")
                                    .errorCode("AUTHENTICATION ERROR")
                                    .timestamp(new Date())
                                    .build()
                    );
                }))
                .accessDeniedHandler(((request, response, accessDeniedException) -> {
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    objectMapper.writeValue(
                            response.getOutputStream(),
                            ExceptionResponse.builder()
                                    .message("FAIL")
                                    .details("ACCESS DENIED")
                                    .timestamp(new Date())
                                    .build()
                    );
                })).and().build();
    }
}