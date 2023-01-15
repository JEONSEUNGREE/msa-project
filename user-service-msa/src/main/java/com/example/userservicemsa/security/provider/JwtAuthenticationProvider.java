package com.example.userservicemsa.security.provider;

import com.example.userservicemsa.exception.JwtBadCredentialsException;
import com.example.userservicemsa.user.service.MemberService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final MemberService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    public JwtAuthenticationProvider(MemberService userDetailsService, BCryptPasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        if (!password.equals("account_token")) {
            // todo 로그인시 권한 추후 추가
            UserDetails user = userDetailsService.loadUserByUsername(username);
            if (user == null) {

                throw new JwtBadCredentialsException("USERNAME IS NOT FOUND. USERNAME=" + username);
            }

            if (!this.passwordEncoder.matches(password, user.getPassword())) {

                throw new JwtBadCredentialsException("PASSWORD IS NOT MATCHED");
            }
            return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());

        }
        // todo 토큰인 경우 권한 추후 추가
        return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}