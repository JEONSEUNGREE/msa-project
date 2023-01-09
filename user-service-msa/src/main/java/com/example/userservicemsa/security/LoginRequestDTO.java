package com.example.userservicemsa.security;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginRequestDTO {
    @NotNull(message = "Email cannot be null")
    @Size(min = 7, message = "Email cannot be less than 7 character")
    @Email()
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 5, message = "Password cannot be less than 5 character")
    private String password;

}
