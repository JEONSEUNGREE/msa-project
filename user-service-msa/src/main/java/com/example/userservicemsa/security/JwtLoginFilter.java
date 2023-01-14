package com.example.userservicemsa.security;

import com.example.userservicemsa.exception.UserNotFoundException;
import com.example.userservicemsa.securityUtil.CookieUtil;
import com.example.userservicemsa.user.service.MemberService;
import com.example.userservicemsa.user.vo.MemberMsVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtProvider jwtProvider;

    private final CookieUtil cookieUtil;
    private final MemberService memberService;



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        log.info("로그인 테스트");
        try {
            LoginRequestDTO loginDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDTO.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUserId(), loginDto.getPassword(),new ArrayList<>()));

        } catch (Exception e) {
            throw new UserNotFoundException("사용자를 찾지 못했습니다.");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws UserNotFoundException{
        Map<String, String> payload = new HashMap<>();
        MemberMsVO memberInfo = null;
        String userId = ((User) authResult.getPrincipal()).getUsername();

        MemberMsVO userInfo = MemberMsVO.builder()
                .userId(userId)
                .build();

        try {
            memberInfo = memberService.getMemberInfo(userInfo);
            payload.put("userId", memberInfo.getMemberId());
            response.addHeader("account_token", jwtProvider.generateToken(payload));
        } catch (Exception e) {
            throw new UserNotFoundException("사용자를 찾을수 없습니다");
        }
    }
}
