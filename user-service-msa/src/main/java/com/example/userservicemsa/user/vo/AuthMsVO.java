package com.example.userservicemsa.user.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthMsVO implements GrantedAuthority {

    String authType;
    @Override
    public String getAuthority() {
        return authType;
    }
}
