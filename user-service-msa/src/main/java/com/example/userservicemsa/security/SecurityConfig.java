package com.example.userservicemsa.security;

import com.example.commonsource.response.ExceptionResponse;
import com.example.userservicemsa.constants.SecurityConstant;
import com.example.userservicemsa.security.filter.JwtAuthenticationFilter;
import com.example.userservicemsa.security.filter.JwtLoginFilter;
import com.example.userservicemsa.security.provider.JwtAuthenticationProvider;
import com.example.userservicemsa.security.securityUtil.CookieUtil;
import com.example.userservicemsa.security.securityUtil.JwtUtil;
import com.example.userservicemsa.user.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.*;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final ObjectMapper objectMapper;

    private final MemberService memberService;

    private final BCryptPasswordEncoder passwordEncoder;

    private final Environment env;

    JwtAuthenticationFilter jwtAuthenticationFilter(JwtUtil jwtProvider, CookieUtil cookieUtil) {
        return new JwtAuthenticationFilter(authenticationManager(),jwtProvider, cookieUtil);
    }

    JwtLoginFilter jwtLoginFilter(JwtUtil jwtProvider, CookieUtil cookieUtil, MemberService memberService) {
        return new JwtLoginFilter(authenticationManager(),memberService, jwtProvider, cookieUtil);
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(customAuthenticationProvider());
    }

    public JwtAuthenticationProvider customAuthenticationProvider() {
        return new JwtAuthenticationProvider(memberService, passwordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(Arrays.asList("http://localhost:1221"));
        configuration.setAllowedMethods(Arrays.asList("HEAD","POST","GET","DELETE","PUT"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setMaxAge(3600L);
        configuration.setAllowCredentials(true);
        configuration.addExposedHeader("account_token");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring().mvcMatchers(SecurityConstant.resourceArray);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtUtil jwtProvider, CookieUtil cookieUtil, MemberService memberService) throws Exception {

        http.httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.authorizeRequests()
                .antMatchers(SecurityConstant.permitAllArray).permitAll()
                .antMatchers(SecurityConstant.authenticationAllArray).authenticated()
                .and()
                .addFilterAt(jwtLoginFilter(jwtProvider, cookieUtil, memberService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter(jwtProvider, cookieUtil), UsernamePasswordAuthenticationFilter.class)
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