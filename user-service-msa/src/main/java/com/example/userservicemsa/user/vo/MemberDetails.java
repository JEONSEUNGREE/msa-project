package com.example.userservicemsa.user.vo;

import org.springframework.security.core.userdetails.User;

public class MemberDetails extends User {

    public MemberDetails(MemberMsVO memberMsVO) {
        super(memberMsVO.getMemberId(), memberMsVO.getMemberPw(), memberMsVO.getAuthorities());
    }
}
