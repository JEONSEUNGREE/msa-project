package com.example.userservicemsa.user.vo;

import lombok.Builder;
import lombok.Getter;

/**
 * A DTO for the {@link com.example.userservicemsa.user.entity.MemberMs} entity
 */

@Getter
@Builder
public class MemberMsVO{

    private String memberId;
    private String userId;
    private String memberPw;
    private String memberName;
    private String memberPhoneNumber;
    private String memberEmail;
    private String memberAddress;
    private String memberRoadAddress;
    private String historyKey;
}