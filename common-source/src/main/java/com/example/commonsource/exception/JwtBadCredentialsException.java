package com.example.commonsource.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtBadCredentialsException extends AuthenticationException {
    public JwtBadCredentialsException(String msg) {
        super(msg);
    }
}
