package com.example.userservicemsa.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupDTO {
    String id;
    String name;
    String email;
    String pw;
    String phoneNum;
    String birthInfo;
    String address;
}
