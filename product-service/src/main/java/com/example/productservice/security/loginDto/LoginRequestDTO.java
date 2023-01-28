package com.example.productservice.security.loginDto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
public class LoginRequestDTO {
    @NotNull(message = "userId cannot be null")
    @Size(min = 7, message = "userId cannot be less than 7 character")
    private String userId;

    @NotNull(message = "Password cannot be null")
    @Size(min = 5, message = "Password cannot be less than 5 character")
    private String password;

}