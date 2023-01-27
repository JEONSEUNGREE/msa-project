package com.example.orderservice.order.controller;

import com.example.commonsource.constant.CommonOrderStatus;
import com.example.commonsource.orderDto.OrderResultDto;
import com.example.commonsource.orderDto.OrderViewDto;
import com.example.orderservice.RestDocsConfiguration;
import com.example.orderservice.interceptor.CommonArgumentResolver;
import com.example.orderservice.interceptor.LoginInfo;
import com.example.orderservice.order.service.OrderService;
import com.example.orderservice.order.util.UserUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;


import javax.transaction.Transactional;
import java.util.List;

import static com.example.orderservice.order.util.ApiDocumentUtils.getDocumentRequest;
import static com.example.orderservice.order.util.ApiDocumentUtils.getDocumentResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(OrderController.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "docs.api.com")
@TestPropertySource(locations = "classpath:/application.yaml")
@Import(RestDocsConfiguration.class)
@AutoConfigureMockMvc(addFilters=false)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    @MockBean
    private UserUtil userUtil;

    @BeforeEach
    public void setUp() {
        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(new OrderControllerTest())
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .setCustomArgumentResolvers(new CommonArgumentResolver())
                .build();
    }

    @Test
    @WithMockUser
    public void oneUserOrderListTest() throws Exception {

    /* given */
    /* when */
    ResultActions result = this.mockMvc.perform(
            get("/orderList")
                    .header("account_token", "account_token")
                    // 트러블 슈팅
                    // Body = <no character encoding set>
                    // Cannot document request fields as the request body is empty
                    .accept(MediaType.APPLICATION_JSON)
    );
    /* then */
    result.andExpect(status().isOk())
            .andDo(document("getOrderList is success",
                    getDocumentRequest(),
                    getDocumentResponse(),
                    requestHeaders(
                            headerWithName("account_token").description("JWT토큰")
                    ),
                    responseFields(
                            fieldWithPath("status").type(JsonFieldType.STRING).description("상태코드"),
                            fieldWithPath("msg").type(JsonFieldType.STRING).description("메시지"),
                            fieldWithPath("responseData").type(JsonFieldType.ARRAY).description("응답데이터").optional(),
                            fieldWithPath("_links.orderHome.href").type(JsonFieldType.STRING).description("메인주소").optional()
                    )
            ));
    }

    @Test
    @WithMockUser
    public void orderProducts() throws Exception {

        /* given */
        OrderViewDto testProducts = OrderViewDto.builder()
                .productName("TEST_PRODUCTS")
                .categoryId("50")
                .qty(1)
                .build();

        /* when */
        ResultActions result = this.mockMvc.perform(
                post("/order")
                        .header("account_token", "account_token")
                        .content(objectMapper.writeValueAsString(testProducts))
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        /* then */
        result.andExpect(status().isOk())
                .andDo(document("order is success",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestHeaders(
                                headerWithName("account_token").description("JWT토큰")
                        ),
                        requestFields(
                                fieldWithPath("productName").type(JsonFieldType.STRING).description("상품이름"),
                                fieldWithPath("categoryId").type(JsonFieldType.STRING).description("카테고리아이디"),
                                fieldWithPath("qty").type(JsonFieldType.NUMBER).description("수량")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING).description("상태코드"),
                                fieldWithPath("msg").type(JsonFieldType.STRING).description("메시지"),
                                fieldWithPath("responseData").type(JsonFieldType.STRING).description("응답데이터"),
                                fieldWithPath("_links.orderHome.href").type(JsonFieldType.STRING).description("메인주소").optional()
                        )
                ));
    }
}