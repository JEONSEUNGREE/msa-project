package com.example.userservicemsa.user.service;


import com.example.userservicemsa.user.dto.SignupDTO;
import com.example.userservicemsa.user.vo.MemberMsVO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    public MemberMsVO getMemberInfo(MemberMsVO memberMsVO) throws Exception;

    public boolean signupMember(SignupDTO signupDTO);
}
