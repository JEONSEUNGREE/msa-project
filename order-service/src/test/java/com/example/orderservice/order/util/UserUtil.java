package com.example.orderservice.order.util;

import com.example.orderservice.security.loginDto.LoginRequestDTO;
import com.example.orderservice.userServiceClient.UserServiceClient;
import feign.Response;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.mock.mockito.MockBean;


@TestComponent
public class UserUtil {

    private UserServiceClient userServiceClient;

    public String getAccessToken(String userId, String password) {

        LoginRequestDTO loginRequestDTO = LoginRequestDTO.builder()
                .userId(userId)
                .password(password)
                .build();

        Response login = userServiceClient.loginVersion1(loginRequestDTO);

        return login.headers().get("account_token").toString();
    }
}
