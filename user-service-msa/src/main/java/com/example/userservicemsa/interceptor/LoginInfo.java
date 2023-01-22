package com.example.userservicemsa.interceptor;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class LoginInfo {

    public String userId;

    public String jwtToken;

}
