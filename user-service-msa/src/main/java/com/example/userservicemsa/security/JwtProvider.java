package com.example.userservicemsa.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.userservicemsa.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtProvider implements AuthenticationProvider {

    private String SECRET_KEY;

    @Value("${spring.application.name}")
    private String ISSUER;

    @Value("${token.expiration_time}")
    private long TOKEN_VALIDATION_SECOND;

    @Value("${token.expiration_time}")
    private long REFRESH_TOKEN_VALIDATION_TIME;

    private final MemberService memberService;

    private Algorithm getSigningKey(String secretKey) {
        return Algorithm.HMAC256(secretKey);
    }

    private Map<String, Claim> getAllClaims(DecodedJWT token) {
        return token.getClaims();
    }

    public String getUserIdFromToken(String token) {
        DecodedJWT verifiedToken = validateToken(token);
        return verifiedToken.getClaim("userId").asString();
    }

    private JWTVerifier getTokenValidator() {
        return JWT.require(getSigningKey(SECRET_KEY))
                .withIssuer(ISSUER)
                .build();
    }

    public String generateToken(Map<String, String> payload) {
        return doGenerateToken(TOKEN_VALIDATION_SECOND, payload);
    }

    public String generateRefreshToken(Map<String, String> payload) {
        return doGenerateToken(REFRESH_TOKEN_VALIDATION_TIME, payload);
    }

    private String doGenerateToken(long expireTime, Map<String, String> payload) {

        return JWT.create()
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime))
                .withClaim("userId", payload.get("userId"))
                .withPayload(payload)
                .withIssuer(ISSUER)
                .sign(getSigningKey(SECRET_KEY));
    }

    private DecodedJWT validateToken(String token) throws JWTVerificationException {
        JWTVerifier validator = getTokenValidator();
        return validator.verify(token);
    }

    public boolean isTokenExpired(String token) {
        try {
            DecodedJWT decodedJWT = validateToken(token);
            return false;
        } catch (JWTVerificationException e) {
            return true;
        }
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = memberService.loadUserByUsername
                ((String) authentication.getPrincipal());

        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}