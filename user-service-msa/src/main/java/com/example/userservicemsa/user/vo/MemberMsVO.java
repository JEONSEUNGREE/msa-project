package com.example.userservicemsa.user.vo;

import com.example.userservicemsa.user.entity.AuthMs;
import com.example.userservicemsa.user.entity.MemberMs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    private Collection<AuthMsVO> authorities;
    public Collection<GrantedAuthority> getAuthorites() {

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (AuthMsVO auth : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(auth.getAuthority()));
        }
        return grantedAuthorities;
    }
}