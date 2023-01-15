package com.example.userservicemsa.security.filter;

import com.example.userservicemsa.exception.UserNotFoundException;
import com.example.userservicemsa.security.securityUtil.JwtUtil;
import com.example.userservicemsa.security.loginDto.LoginRequestDTO;
import com.example.userservicemsa.security.securityUtil.CookieUtil;
import com.example.userservicemsa.user.service.MemberService;
import com.example.userservicemsa.user.vo.MemberMsVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    private JwtUtil jwtProvider;
    private CookieUtil cookieUtil;
    private MemberService memberService;

    public JwtLoginFilter(
            AuthenticationManager authenticationManager,
            MemberService memberService,
            JwtUtil jwtProvider,
            CookieUtil cookieUtil) {
        super(authenticationManager);
        this.memberService = memberService;
        this.jwtProvider = jwtProvider;
        this.cookieUtil = cookieUtil;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            LoginRequestDTO loginDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDTO.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUserId(), loginDto.getPassword(), new ArrayList<>()));

        } catch (Exception e) {
            throw new UserNotFoundException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws UserNotFoundException{
        Map<String, String> payload = new HashMap<>();
        MemberMsVO memberInfo = null;
        String userId = (String)authResult.getPrincipal();

        // 추가 정보 claim에 넣는 부분
        MemberMsVO userInfo = MemberMsVO.builder()
                .userId(userId)
                .build();

        try {
            memberInfo = memberService.getMemberInfo(userInfo);
            payload.put("userId", memberInfo.getUserId());
            response.addHeader("account_token", jwtProvider.generateToken(payload));
        } catch (Exception e) {
            throw new UserNotFoundException("사용자를 찾을 수 없습니다");
        }
    }
}
