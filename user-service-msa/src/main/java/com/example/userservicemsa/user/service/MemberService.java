package com.example.userservicemsa.user.service;


import com.example.commonsource.orderDto.OrderResultDto;
import com.example.commonsource.response.JsonResponse;
import com.example.userservicemsa.interceptor.annotation.CurrentUser;
import com.example.userservicemsa.user.dto.SignupDTO;
import com.example.userservicemsa.user.vo.MemberMsVO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {

    public MemberMsVO getMemberInfo(MemberMsVO memberMsVO) throws Exception;

    public boolean signupMember(SignupDTO signupDTO);

    public List<OrderResultDto> getOrderList(String account_token);
}
