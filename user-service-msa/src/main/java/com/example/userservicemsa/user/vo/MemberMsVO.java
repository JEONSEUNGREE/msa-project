package com.example.userservicemsa.user.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.userservicemsa.user.entity.MemberMs} entity
 */

@NoArgsConstructor
@Getter
public class MemberMsVO implements Serializable {
    private String memberId;
    private String userId;
    private String memberName;
    private String memberPhoneNumber;
    private String memberEmail;
    private String memberAddress;
    private String memberRoadAddress;
    private String historyKey;
}