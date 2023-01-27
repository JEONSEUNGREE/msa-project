package com.example.orderservice.order.util;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class MockUserService {

    public static void setupGetOfficeResponse(WireMockServer mockCacheApi) {
        mockCacheApi.stubFor(WireMock.get(WireMock.urlMatching("/offices/[1-9]"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("payload/get-office-response.json")
                )
        );
    }

}
