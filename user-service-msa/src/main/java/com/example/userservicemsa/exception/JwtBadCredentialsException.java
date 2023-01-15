package com.example.userservicemsa.exception;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

public class JwtBadCredentialsException extends AuthenticationException {
    public JwtBadCredentialsException(String msg) {
        super(msg);
    }
}
