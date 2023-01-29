package com.example.productservice.constants;

import com.example.productservice.security.SecurityConfig;

public class SecurityConstant {
    /**
     * Resource 대상
     * @see SecurityConfig
     */
    public static final String[] resourceArray = new String[] { "/css/**", "/fonts/**", "/images/**", "/js/**",
            "/modules/**", "/h2-console/**", "/swagger-ui/**" };

    /**
     * 권한제외 대상
     * @see SecurityConfig
     */
    public static final String[] permitAllArray = new String[] { "/login", "/home", "/signup", "/docs/index.html"};

    /**
     * 권한 인증 상대
     * @see SecurityConfig
     */
    public static final String[] authenticationAllArray = new String[] { "/**"};

}