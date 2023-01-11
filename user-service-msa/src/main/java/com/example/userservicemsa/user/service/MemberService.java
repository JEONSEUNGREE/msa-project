package com.example.userservicemsa.user.service;


import com.example.userservicemsa.user.dto.SignupDTO;
import com.example.userservicemsa.user.entity.MemberMs;
import com.example.userservicemsa.user.vo.MemberMsVO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface MemberService extends UserDetailsService {

    public MemberMsVO getMemberInfo(MemberMsVO memberMsVO);

    public boolean signupMember(SignupDTO signupDTO);
}
